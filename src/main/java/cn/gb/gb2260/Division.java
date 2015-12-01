package cn.gb.gb2260;

public class Division {
    private String name;
    private String code;
    private String revision;
    private String province;
    private String prefecture;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPrefecture() {
        return prefecture;
    }

    public void setPrefecture(String prefecture) {
        this.prefecture = prefecture;
    }

    @Override
    public String toString() {
        return (province == null ? "" : province) + (prefecture == null ? "" : prefecture) + name;
    }
}
