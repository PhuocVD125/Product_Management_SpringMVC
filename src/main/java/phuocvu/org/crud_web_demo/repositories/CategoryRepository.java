package phuocvu.org.crud_web_demo.repositories;
// learn about repository
/*  Repository là thành phần thuộc Model,
    đảm nhiệm vai trò trung gian giữa các ứng dụng và nguồn dữ liệu(thường là cơ sở dữ liệu)
    thực hiện cc hoạt động lưu trữ và truy xuất dữ liệu

    + Sử dụng Repository có thể tách biệt logic xử lý dữ liệu khỏi giao diện user,
      giúp code dễ quản lý, mở rộng, kiểm thử
    + Các phương thức của Repository được sử dụng trong Controller để thực hiện các tác vụ với dữ liệu
*/
/*
    Interface CategoryRepository này được sử dụng để thực hiện các hoạt động
    lưu trữ và truy xuất dữ liệu cho đối tượng "Category"

    CategoryRepository được kế thừa từ CrudRepository

    CrudRepository là interface có sẵn trong framework cung cấp các phương thức cơ bản
    để thao tác vơi cơ sở dữ liệu(CRUD)

    Các phương thức CategoryRepository được sử dụng trong lớp Service hoặc Controller của ứng dụng
    để thực hiện các tác vụ liên quan đến lưu trữ và truy xuất dữ liệu

*/
import org.springframework.data.repository.CrudRepository;
import phuocvu.org.crud_web_demo.models.Category;

public interface CategoryRepository extends CrudRepository<Category, String> {
}
