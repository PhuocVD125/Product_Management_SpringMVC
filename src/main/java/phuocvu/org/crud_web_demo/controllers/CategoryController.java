package phuocvu.org.crud_web_demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import phuocvu.org.crud_web_demo.models.Category;
import phuocvu.org.crud_web_demo.repositories.CategoryRepository;

@Controller
@RequestMapping(path = "categories")
//http:localhost:8081/categories
public class CategoryController {
    @Autowired //Inject "categoryRespository" - Dependency Injection
    // Autowired thực hiện DI đơn giản để khởi tạo 1 object trong 1 object
    // Như ở duới nó tự tìm CategoryRepository để tiêm vào đây nếu chưa có, nó tự tạo

    private CategoryRepository categoryRepository;

    //return name of "jsp file"
    //http:localhost:8081/categories
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getAllCategories(ModelMap modelMap) {
        /*
         Model Map là 1 lớp giúp chuyển dữ liệu đến view bằng cách mang theo các đối tượng hoặc giá trị
         data sent to jsp => ModelMap
         sent data from controller to view

         Sử dụng phương thức của reponsitory nè
         Iterable là 1 kiểu dữ liệu tập hợp các đối tượng
         Vì tròn database sẽ có nhiều đối tượng Category
         */

        Iterable<Category> categories = categoryRepository.findAll();
        modelMap.addAttribute("categories", categories);
        System.out.println("Success");
        return "category";
    }
}
