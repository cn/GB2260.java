package cn.gb.gb2260;

public enum Revision {
    V2014("2014"),
    V2013("2013"),
    V2012("2012"),
    V2011("2011"),
    V2010("2010"),
    V2009("2009"),
    V2008("2008"),
    V2007("2007"),
    V2006("2006"),
    V2005("2005"),
    V200506("200506"),
    V2004("2004"),
    V200409("200409"),
    V200403("200403"),
    V2003("2003"),
    V200306("200306"),
    V2002("2002");

    private final String code;

    Revision(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
