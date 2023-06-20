package phuocvu.org.crud_web_demo.models;

//POJO - Plain Object Java Object
// Là một loại đối tượng đơn giản không đòi hỏi thư viện hay framework đặc biệt
// Tuân thủ các quy tắc để dễ bảo trì:
//    + không phụ thuôc vào lớp hay interface đặc biệt
//    + không có các đối tượng chuyên dụng hay kế thừa từ các lớp cụ thể
//    + có getter, setter
//    + thiết kế để truyền dữ liệu giữa các thành phần của ứng dụng, nhưu truền dât giữa các lớp DAO

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// báo cho spring biết đây là 1 thực thể
@Entity
// khai báo bảng tương ứng với thực thể
@Table(name = "categories")
public class Category {
    // khai báo cột id và cột tương ứng trong trường hợp tên biến khác tên cột trong database
    @Id
    @Column(name = "categoryID")
    private String categoryID;
    @Column(name = "categoryName")
    private String categoryName;
    @Column(name = "description")
    private String description;

    public Category(){}
    public Category(String categoryID, String categoryName, String description) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.description = description;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
