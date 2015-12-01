package cn.gb.gb2260;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class GB2260 {
    private final Revision revision;
    private HashMap<String, String> data;

    public GB2260() throws IOException {
        this(Revision.V2014);
    }

    public GB2260(Revision revision) throws IOException {
        this.revision = revision;
        data = new HashMap<String, String>();
        InputStream inputStream = getClass().getResourceAsStream("/data/" + revision.getCode() + ".txt");
        BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
        while (r.ready()) {
            String line = r.readLine();
            String[] split = line.split("\t");
            data.put(split[0], split[1]);
        }
    }

    public Division findByCode(String code) throws IOException {
        Division division = new Division();
        String name = data.get(code);
        division.setName(name);
        division.setRevision(this.revision.getCode());
        return division;
    }
}
