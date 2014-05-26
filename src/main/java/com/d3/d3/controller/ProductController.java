/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.d3.d3.controller;

import com.d3.d3.model.Image;
import com.d3.d3.model.Product;
import com.d3.d3.model.others.ItemProduct;
import com.d3.d3.model.others.ProductAux;
import com.d3.d3.repository.CategoryRepository;
import com.d3.d3.service.CategoryService;
import com.d3.d3.service.ImageService;
import com.d3.d3.service.ProductService;
import com.d3.d3.validation.ItemProductValidator;
import com.d3.d3.validation.ProductValidator;
import com.d3.d3.validation.others.Functions;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Cristian
 */
@Controller
//@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductService productService;

    @Resource
    private ImageService imageService;

    @Resource
    private CategoryRepository categoryRepository;

    // Determino la vista
    @ModelAttribute("page")
    public String module() {
        return "product";
    }

    @InitBinder(value = "itemproduct")
    protected void initBinderItemProduct(WebDataBinder binder) {
        binder.setValidator(new ItemProductValidator());
    }

    private final String URL = "product";
    private final String INDEX = URL + "/index";
    private final String CREATE = URL + "/create";
    private final String EDIT = URL + "/edit";
    private final String DELETE = URL + "/delete";
    private final String SHOW_ADMIN = URL + "/show";
    private final String SHOW_USER = URL + "/showUser";
    private final String UPD_IMG = URL + "/upload";
    private final String SHOW_ALL_ADMIN = URL + "/showAll";
    private final String SHOW_ALL_USER = URL + "/showAllUser";
    private final String PRODJS = URL + "/productjs";
    private final String PRODCATG = URL + "/productsCatg";

    @InitBinder(value = "productaux")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new ProductValidator());
    }

    @RequestMapping(value = "/admin/product/create", method = RequestMethod.GET)
    public String create(Model model, HttpSession session) {
        String redir = Functions.goAdmin(session);
        if (redir.isEmpty()) {
            model.addAttribute("productaux", new ProductAux());
            model.addAttribute("catgs", categoryRepository.findAll());
            redir = CREATE;
        }
        return redir;
    }

    @Autowired
    private ServletContext servletContext;  //Usaremos esto para calcular la ruta de subida de la imágenes

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    private String uploadImage(MultipartFile image, Product prod) {
        String message;
        try {
            byte[] bytes = image.getBytes();

            // Creating the directory to store file
            String rootPath = System.getProperty("catalina.home");
            File dir = new File(rootPath + File.separator + "tmpFiles");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Create the file on server
            File serverFile = new File(servletContext.getRealPath("/")
                    + "assets" + File.separator + "img" + File.separator + image.getOriginalFilename());
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();

            System.err.println("Server File Location=" + serverFile.getAbsolutePath());
            System.err.println(dir.getAbsolutePath() + File.separator + image.getName());

            message = "You successfully uploaded file=" + image.getName() + "<br />";

            Image img = new Image();
            img.setImage(serverFile.getName());//serverFile.getAbsolutePath());
            img.setIdProd(prod);
            imageService.create(img);

        } catch (IOException e) {
            return "You failed to upload " + image.getName() + " => " + e.getMessage();
        }
        return message;
    }

    @RequestMapping(value = "/admin/product/uploadImages/{id}", method = RequestMethod.GET)
    public String updGet(@PathVariable String id, Model m, HttpSession session) {
        String redir = Functions.goAdmin(session);
        if (redir.isEmpty()) {
            int id_ = Functions.getInt(id);
            if (id_ <= 0) {
                m.addAttribute("error", "{product.notfound}");
                return PRODJS;
            }
            if (productService.findById(id_) == null) {
                m.addAttribute("error", "{product.notfound}");
                return PRODJS;
            }
            // El producto existe
            m.addAttribute("idProd", id_);
            return UPD_IMG;
        }
        return redir;
    }

    @RequestMapping(value = "/admin/product/uploadImages/{id}", method = RequestMethod.POST)
    public @ResponseBody
    String uploadMultipleFileHandler(@RequestParam("id") Integer id,
            @RequestParam("image") MultipartFile[] images, Model m, HttpSession session) {
        String redir = Functions.goAdmin(session);
        if (redir.isEmpty()) {
            //int id_ = Functions.getInt(id);
            if (id <= 0) {
                m.addAttribute("error", "{product.notfound}");
                redir = PRODJS;
            } else {
                Product product = productService.findById(id);
                if (product == null) {
                    m.addAttribute("error", "{product.notfound}");
                    redir = PRODJS;
                } else {
                    // Si todo ha ido bien, subo las fotos
                    String message = "";
                    for (MultipartFile image : images) {
                        message += uploadImage(image, product) + "\n";
                    }
                    System.out.println(message);
                    redir = "redirect:/admin/product/show/" + id + ".html";
                }
            }
        }
        return redir;
    }

    @RequestMapping(value = "/admin/product/create", method = RequestMethod.POST)
    public String create_post(@ModelAttribute(value = "productaux") @Valid ProductAux product,
            BindingResult errors, Model m/*, @RequestParam("image") MultipartFile[] images*/, HttpSession session) {
        String redir = Functions.goAdmin(session);
        if (redir.isEmpty()) {
            //Aquí pruebo

            System.out.println("ERRORES:");
            for (ObjectError e : errors.getAllErrors()) {
                System.out.println(e);
            }

            if (errors.hasErrors()) {
                System.out.println("Error validación");
                m.addAttribute("catgs", categoryRepository.findAll());
                return CREATE;
            }
            Product prod = new Product(product, categoryRepository.findOne(product.getIdCat()));
            boolean insert = productService.create(prod);
            if (!insert) {
                m.addAttribute("error", "No se ha podido insertar");
            } else {
                /* // Si todo ha ido bien, subo las fotos
                 String message = "";
                 for (MultipartFile image : images) {
                 message += uploadImage(image, product);
                 }*/
                m.addAttribute("ok", "Producto " + product.getName() + " insertado");
            }
            return PRODJS;
        }
        return redir;
    }

    @RequestMapping(value = "/admin/product/show/{id}", method = RequestMethod.GET)
    public String show_admin(@PathVariable String id, Model m, HttpSession session) {
        String redir = Functions.goAdmin(session);
        if (redir.isEmpty()) {
            int id_ = Functions.getInt(id);
            if (id_ <= 0) {
                m.addAttribute("error", "Producto no encontrado");
            } else {
                Product p = productService.findById(id_);
                if (p != null) {
                    m.addAttribute("product", p);
                    return SHOW_ADMIN;
                } else {
                    m.addAttribute("error", "Producto no encontrado");
                }
            }
            return PRODJS;
        }
        return redir;
    }

    @RequestMapping(value = "/product/show/{id}", method = RequestMethod.GET)
    public String show_user(@PathVariable String id, Model m) {
        int id_ = Functions.getInt(id);
        if (id_ <= 0) {
            m.addAttribute("error", "Producto no encontrado");
        } else {
            Product p = productService.findById(id_);
            if (p != null) {
                m.addAttribute("product", p);
                m.addAttribute("itemproduct", new ItemProduct(id_));
                return SHOW_USER;
            } else {
                m.addAttribute("error", "Producto no encontrado");
            }
        }
        return PRODJS;
    }

    @RequestMapping(value = {"/product/all", "/product/", "/product/index"}, method = RequestMethod.GET)
    public String showAllUser(@RequestParam(value = "error", defaultValue = "",
            required = true) String error, @RequestParam(value = "ok", defaultValue = "",
                    required = true) String ok, ModelMap model, HttpSession session) {
        String redir = Functions.goAdmin(session);
        if (redir.isEmpty()) {
            List<Product> lp = productService.findAll();
            if (!ok.isEmpty()) {
                model.addAttribute("ok", ok);
            }
            if (!error.isEmpty()) {
                model.addAttribute("error", error);
            }
            model.addAttribute("products", lp);
            return SHOW_ALL_USER;

        }
        return redir;
    }

    @RequestMapping(value = {"/admin/product/all", "/admin/product/", "/admin/product", "/admin/product/index"}, method = RequestMethod.GET)
    public String showAll(
            @RequestParam(value = "error", defaultValue = "", required = true) String error,
            @RequestParam(value = "ok", defaultValue = "", required = true) String ok,
            ModelMap model, HttpSession session) {
        String redir = Functions.goAdmin(session);
        if (redir.isEmpty()) {
            List<Product> lp = productService.findAll();
            if (!ok.isEmpty()) {
                model.addAttribute("ok", ok);
            }
            if (!error.isEmpty()) {
                model.addAttribute("error", error);
            }
            model.addAttribute("products", lp);
            return SHOW_ALL_ADMIN;
        }
        return redir;
    }

    @RequestMapping(value = "/admin/product/edit/{id}", method = RequestMethod.GET)
    public String edit_get(@PathVariable String id, Model m, HttpSession session) {
        String redir = Functions.goAdmin(session);
        if (redir.isEmpty()) {
            int id_ = Functions.getInt(id);
            if (id_ <= 0) {
                m.addAttribute("error", "Producto no encontrado");
            } else {
                Product p = productService.findById(id_);
                if (p == null) {
                    m.addAttribute("error", "Producto no encontrado");
                } else {
                    ProductAux prod = new ProductAux(p);
                    m.addAttribute("productaux", prod);
                    m.addAttribute("catgs", categoryRepository.findAll());
                    return EDIT;
                }
            }
            return PRODJS;
        }
        return redir;
    }

    @RequestMapping(value = "/admin/product/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable String id, Model m, HttpSession session) {
        String redir = Functions.goAdmin(session);
        if (redir.isEmpty()) {
            int id_ = Functions.getInt(id);
            if (id_ <= 0) {
                m.addAttribute("error", "Producto no encontrado");
            } else {
                boolean delete = productService.delete(id_);
                if (!delete) {
                    m.addAttribute("error", "Producto no encontrado");
                } else {
                    m.addAttribute("ok", "Producto borrado");
                }
            }
            return PRODJS;
        }
        return redir;
    }

    @RequestMapping(value = "/admin/product/edit/{id}", method = RequestMethod.POST)
    public String edit_post(@ModelAttribute(value = "productaux") @Valid ProductAux product,
            BindingResult errors, Model m, HttpSession session) {
        String redir = Functions.goAdmin(session);
        if (redir.isEmpty()) {
            if (errors.hasErrors()) {
                System.out.println("ERRORES");
                m.addAttribute("catgs", categoryRepository.findAll());
                return EDIT;
            }
            Product prod = new Product(product, categoryRepository.findOne(product.getIdCat()));
            boolean edit = productService.update(prod);
            if (!edit) {
                m.addAttribute("error", "product.edit.error");
            } else {
                m.addAttribute("ok", "product.edit.ok");
            }
            return PRODJS;
        }
        return redir;
    }

    @Resource
    private CategoryService categoryService;

    @RequestMapping(value = "/product/category/{id}", method = RequestMethod.GET)
    public String showProductWithCatgID(@PathVariable String id, Model m) {
        int id_ = Functions.getInt(id);
        List<Product> products = null;
        if (id_ > 0) {
            products = categoryService.findProductWithIdCategory(id_);
            if (products == null) {
                products = new LinkedList<Product>();
            }
        }
        m.addAttribute("category", categoryService.findById(id_).getName());
        m.addAttribute("products", products);
        return PRODCATG;
    }
}
