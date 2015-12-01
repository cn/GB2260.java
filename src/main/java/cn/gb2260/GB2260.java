package cn.gb2260;

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

            if (Pattern.matches("^\\d{2}0{4}$", code)) {
                Division division = new Division();
                division.setCode(code);
                division.setName(name);
                provinces.add(division);
            }
        }
    }

    public Division get(String code) {
        if (code.length() != 6) {
            throw new InvalidCodeException("Invalid code");
        }

        if (!data.containsKey(code)) {
            return null;
        }

        Division division = new Division();
        division.setName(data.get(code));
        division.setRevision(getRevision().getCode());
        division.setCode(code);

        if (Pattern.matches("^\\d{2}0{4}$", code)) {
            return division;
        }

        String provinceCode = code.substring(0, 2) + "0000";
        division.setProvince(data.get(provinceCode));

        if (Pattern.matches("^\\d{4}0{2}$", code)) {
            return division;
        }

        String prefectureCode = code.substring(0, 4) + "00";
        division.setPrefecture(data.get(prefectureCode));

        division.setRevision(this.revision.getCode());
        return division;
    }

    public Revision getRevision() {
        return revision;
    }

    public ArrayList<Division> getProvinces() {
        return provinces;
    }

    public ArrayList<Division> getPrefectures(String code)  {
        ArrayList<Division> rv = new ArrayList<Division>();

        if (!Pattern.matches("^\\d{2}0{4}$", code)) {
            throw new InvalidCodeException("Invalid province code");
        }

        if (!data.containsKey(code)) {
            throw new InvalidCodeException("Province code not found");
        }

        Division province = get(code);

        Pattern pattern = Pattern.compile("^" + code.substring(0, 2) + "\\d{2}00$");
        for (String key : data.keySet()) {
            if (pattern.matcher(key).matches()) {
                Division division = get(key);
                division.setProvince(province.getName());
                rv.add(division);
            }
        }

        return rv;
    }

    public ArrayList<Division> getCounties(String code)  {
        ArrayList<Division> rv = new ArrayList<Division>();

        if (!Pattern.matches("^\\d+[1-9]0{2,3}$", code)) {
            throw new InvalidCodeException("Invalid prefecture code");
        }

        if (!data.containsKey(code)) {
            throw new InvalidCodeException("Prefecture code not found");
        }

        Division prefecture = get(code);
        Division province = get(code.substring(0, 2) + "0000");

        Pattern pattern = Pattern.compile("^" + code.substring(0, 4) + "\\d+$");
        for (String key : data.keySet()) {
            if (pattern.matcher(key).matches()) {
                Division division = get(key);
                division.setProvince(province.getName());
                division.setPrefecture(prefecture.getName());
                rv.add(division);
            }
        }

        return rv;
    }
}
