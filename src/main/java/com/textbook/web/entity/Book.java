package com.textbook.web.entity;

public class Book {
    private Integer id;
    private String tname;      // 教材名
    private String tauthor;    // 作者
    private String press;      // 出版社
    private Double price;      // 价格
    private String isbn;       // 教材号
    private String tdescript;  // 教材描述
    private Integer count;     // 库存
    private String pic;        // 教材图片
    private String type;       // 教材类型

    public Book() {
    }

    // Getter 和 Setter 方法
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTname() { return tname; }
    public void setTname(String tname) { this.tname = tname; }

    public String getTauthor() { return tauthor; }
    public void setTauthor(String tauthor) { this.tauthor = tauthor; }

    public String getPress() { return press; }
    public void setPress(String press) { this.press = press; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getTdescript() { return tdescript; }
    public void setTdescript(String tdescript) { this.tdescript = tdescript; }

    public Integer getCount() { return count; }
    public void setCount(Integer count) { this.count = count; }

    public String getPic() { return pic; }
    public void setPic(String pic) { this.pic = pic; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}