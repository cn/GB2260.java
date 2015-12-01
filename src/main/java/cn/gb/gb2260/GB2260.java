package cn.gb.gb2260;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class GB2260 {
    private final Revision revision;
    private HashMap<String, String> data;
    private ArrayList<Division> provinces;

    public GB2260() throws IOException {
        this(Revision.V2014);
    }

    public GB2260(Revision revision) throws IOException {
        this.revision = revision;
        data = new HashMap<String, String>();
        provinces = new ArrayList<Division>();
        InputStream inputStream = getClass().getResourceAsStream("/data/" + revision.getCode() + ".txt");
        BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
        while (r.ready()) {
            String line = r.readLine();
            String[] split = line.split("\t");
            String code = split[0];
            String name = split[1];

            data.put(code, name);

            if (Pattern.matches(".*0{4}$", code)) {
                Division division = new Division();
                division.setCode(code);
                division.setName(name);
                provinces.add(division);
            }
        }
    }

    public Division findByCode(String code) throws IOException {
        Division division = new Division();
        String name = data.get(code);
        division.setName(name);
        division.setRevision(this.revision.getCode());
        return division;
    }

    public Revision getRevision() {
        return revision;
    }

    public ArrayList<Division> getProvinces() {
        return provinces;
    }
}
