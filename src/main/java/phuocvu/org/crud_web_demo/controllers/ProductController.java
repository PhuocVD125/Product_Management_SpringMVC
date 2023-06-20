package phuocvu.org.crud_web_demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import phuocvu.org.crud_web_demo.models.Category;
import phuocvu.org.crud_web_demo.models.Product;
import phuocvu.org.crud_web_demo.repositories.CategoryRepository;
import phuocvu.org.crud_web_demo.repositories.ProductRepository;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping(path = "products")
//http:localhost:8081/products
public class ProductController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    //http:localhost:8081/products/getProductsByCategoryID/{categoryID}
    //@Pathvariable để trích xuất giá trị từ một phần tử trong URL và sử dụng nó trong phương thức xử lý yêu cầu
    @RequestMapping(value = "/getProductsByCategoryID/{categoryID}", method = RequestMethod.GET)
    public String getProductsByCategoryID(ModelMap modelMap, @PathVariable String categoryID) {
        Iterable<Product> products = productRepository.findByCategoryID(categoryID);
        modelMap.addAttribute("products", products);
        return "productList"; // productList.jsp
    }

    @RequestMapping(value = "changeCategory/{productID}", method = RequestMethod.GET)
    public String changCategory(ModelMap modelMap, @PathVariable String productID) {
        Iterable<Category> categories = categoryRepository.findAll();
    /*
    Vi gia tri cua product co the Null
    Optional<Product> product = productRepository.findById(productID);
    Optional<T>, như đã đề cập trước đó, là một lớp trong Java để đảm bảo rằng một giá trị có thể tồn tại hoặc không tồn tại.
     */
        //modelMap.addAttribute("product", product);
        modelMap.addAttribute("categories", categories);
        modelMap.addAttribute("product", productRepository.findById(productID).get()); // tương tự cách sử dụng optional
        return "updateProduct"; //updateProduct.jsp
    }

    // Insert a new product
    // Insert's page
    @RequestMapping(value = "/insertProduct", method = RequestMethod.GET)
    public String insertProduct(ModelMap modelMap) {
        modelMap.addAttribute("product", new Product());
        modelMap.addAttribute("categories", categoryRepository.findAll());
        return "insertProduct";
    }


    //over loading
    // controller insert product
    @RequestMapping(value = "/insertProduct", method = RequestMethod.POST)
    public String insertProduct(ModelMap modelMap,
                               @Valid @ModelAttribute("product") Product product,
                               BindingResult bindingResult //validation
    ) {
        if (bindingResult.hasErrors()) {
            return "insertProduct";
        }
        try {
            // random uuid => productID
            product.setProductID(UUID.randomUUID().toString());
            System.out.println(product.getProductID());
            productRepository.save(product);
            System.out.println("done");
            return "redirect:/categories";
        } catch (Exception e) {
            System.out.println(e.toString());
            modelMap.addAttribute("error", e.toString());
            return "insertProduct";
        }
    }

    // Delete a product
    @RequestMapping(value = "/deleteProduct/{productID}", method = RequestMethod.POST)
    public String deleteProduct(ModelMap modelMap, @PathVariable String productID) {
        productRepository.deleteById(productID);
        return "redirect:/categories";
    }

    /*
    Note:
    @ModelAttribute được sử dụng để đánh dấu một tham số
    trong phương thức xử lý yêu cầu (request handling method)
    là một đối tượng mô hình (model object). Nó cho phép bạn
    truy cập hoặc sửa đổi các thuộc tính của đối tượng mô hình trong quá trình xử lý yêu cầu.

    @ModelAttribute("product") Product product có ý nghĩa là bạn đang khai báo một tham số product
    trong phương thức updateProduct và đánh dấu nó là một đối tượng mô hình có tên là "product".
    Khi Spring xử lý yêu cầu, nó sẽ cố gắng liên kết các thông tin từ yêu cầu
    (như các tham số form hoặc các giá trị trong mô thức POST)
    với thuộc tính của đối tượng product.

    Nói đơn giản: + view khởi tạo hay lấy 1 product từ view trước
                    (view product đó)có thuộc tính categoryID mang giá trị của người nhập
                  + view gửi thông tin này đến updateProduct
                  + tạo 1 đối tượng product mới tương ứng với product từ view, xác định bằng productID
                  + và update các thông tin thôi
     */
    @RequestMapping(value = "/updateProduct/{productID}", method = RequestMethod.POST)
    public String updateProduct(ModelMap modelMap,
                                @Valid @ModelAttribute("product") Product product, // product từ view nè, chú ý @Valid -> xác thực tự động tạo bindingResult
                                BindingResult bindingResult,
                                @PathVariable String productID
    ) {
        System.out.println("start");
        Iterable<Category> categories = categoryRepository.findAll();
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.toString());
            System.out.println("has ERROS");
            modelMap.addAttribute("categories", categories);
            return "updateProduct";
        }
        try {
            if (productRepository.findById(productID).isPresent()) {
                Product foundProduct = productRepository
                        .findById(product.getProductID()).get();
                if (!product.getProductName().trim().isEmpty()) {
                    foundProduct.setProductName(product.getProductName());
                }
                if (!product.getCategoryID().isEmpty()) {
                    foundProduct.setCategoryID(product.getCategoryID());
                }
                //is empty => "" OR NULL
                if (!product.getDescription().trim().isEmpty()) {
                    foundProduct.setDescription(product.getDescription());
                }
                if (product.getPrice() > 0) {
                    foundProduct.setPrice(product.getPrice());
                }
                productRepository.save(foundProduct);
            }
        } catch(Exception e){
            return "updateProduct";//updateProduct.jsp
        }
        return "redirect:/products/getProductsByCategoryID/" + product.getCategoryID();
        /*
        redirect là một cách để chuyển hướng (redirect) trình duyệt của người dùng từ một URL hiện tại đến một URL mới.
         Khi bạn sử dụng redirect trong một phương thức xử lý yêu cầu (request handling method),
         trình duyệt sẽ được chuyển hướng đến URL mới mà bạn đã chỉ định.
         */
    }
}
