# GB2260.java
The Java implementation for looking up Chinese administrative divisions.

## GB/T 2260

[![GB/T 2260](https://img.shields.io/badge/GB%2FT%202260-v0.2-blue.svg)](https://github.com/cn/GB2260)
[![Build Status](https://img.shields.io/travis/cn/GB2260.java.svg?style=flat)](https://travis-ci.org/cn/GB2260.java)
[![Coverage](https://img.shields.io/coveralls/cn/GB2260.java.svg?style=flat)](https://coveralls.io/r/cn/GB2260.java)

The latest GB/T 2260 codes. Read the [GB2260 Specification](https://github.com/cn/GB2260/blob/v0.2/spec.md).

## Build

Install with nvm:

    $ git submodule update --init
    $ mvn clean package
    $ mvn install:install-file -Dfile=GB2260-0.1.jar -DgroupId=cn.gb2260 -DartifactId=GB2260 -Dversion=0.1 -Dpackaging=jar

Update the pom.xml file in project

```xml
<dependencies>
    ...
    <dependency>
        <groupId>cn.gb2260</groupId>
        <artifactId>GB2260</artifactId>
        <version>0.1</version>
    </dependency>
    ...
</dependencies>
```

## Usage

```java
GB2260 gb = new GB2260();
```

## GB2260

```java
GB2260 gb = new gb2260.GB2260(); // with default revision 2014
GB2260 gb = new gb2260.GB2260(Revision.V2002); // specify the revision
```

Interface for GB2260.

### .getDivision(code)

Get division for the given code.

```java
Division division = gb.getDivision("110105")
// 北京市 市辖区 朝阳区

division.getName()
// 朝阳区
division.getCode()
// 110105
division.getRevision()
// 2014

division.getProvince()
// 北京市
division.getPrefecture()
// 市辖区

division.toString()
// 北京市 市辖区 朝阳区
```

### .getProvinces()

Return a list of provinces in Division data structure.

```java
gb.getProvinces()
```

### .getPrefectures(code)

Return a list of prefecture level cities in Division data structure.

```java
gb.getPrefectures("110000")
```

### .getCounties(code)

Return a list of counties in Division data structure.

```java
gb.getCounties("110100")
```

## revisions

`Revision` contains a list of available revisions.

```java
Revision.V2014.getCode() // return 2014
```

## License

MIT.
