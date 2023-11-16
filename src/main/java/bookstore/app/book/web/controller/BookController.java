package bookstore.app.book.web.controller;

import bookstore.app.book.config.AppConfig;
import bookstore.app.book.dto.BookDto;
import bookstore.app.book.dto.CategoryDto;
import bookstore.app.book.service.IBookService;
import bookstore.app.book.service.ICategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
@RequestMapping("/books")
@AllArgsConstructor
public class BookController  {

    private final IBookService bookService;
    private final AppConfig appConfig;
    private final ICategoryService categoryService;

    @GetMapping("/{id}")
    public String getBook(@PathVariable String id, Model model){
        try {
            BookDto book= bookService.getById(Long.valueOf(id));
            model.addAttribute("book",book);
            List<CategoryDto> categories = (List<CategoryDto>) categoryService.getAll();
            model.addAttribute("categories",categories);
            return "book-details";
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "book-details";
    }

    //add book
    @GetMapping("/add")
    public String getAdd(Model model){
        BookDto book = bookService.getById((long)-1);
        List<CategoryDto> categories = (List<CategoryDto>) categoryService.getAll();
        model.addAttribute("categories",categories);
        model.addAttribute("book",book);
        return "add-book";
    }

    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute("book") BookDto book,
                          BindingResult result,
                          @RequestParam("imgcover") MultipartFile multipartFile
    ) {
        try {
            if(result.hasErrors()){
                System.out.println(result.getAllErrors());
                return "add-book";
            }
            if(!multipartFile.isEmpty()){
                String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
                book.setImgCover(fileName);
                BookDto tmpBook = bookService.create(book);
                String uploadDir = "./" + appConfig.getFileUploadPath() + "/" + tmpBook.getId();
                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)){
                    Files.createDirectories(uploadPath);
                }
                try {
                    InputStream inputStream = multipartFile.getInputStream();
                    Path filePath = uploadPath.resolve(fileName);
                    Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException IOE){
                    IOE.printStackTrace();
                }
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }

    @PutMapping("/{id}")
    public String updateBook( @Valid @ModelAttribute ("book") BookDto book,
                              @PathVariable String id,
                              @RequestParam("imgcover") MultipartFile multipartFile,
                              BindingResult result
    ) throws Exception {
        try {
            if(result.hasErrors()){
                System.out.println(result.getAllErrors());
                return "book-details";
            }
            if (!multipartFile.isEmpty()){
                String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
                book.setImgCover(fileName);
                String uploadDir = "./" + appConfig.getFileUploadPath() + "/" + book.getId();
                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)){
                    Files.createDirectories(uploadPath);
                }
                try {
                    InputStream inputStream = multipartFile.getInputStream();
                    Path filePath = uploadPath.resolve(fileName);
                    Files.copy(inputStream,filePath,StandardCopyOption.REPLACE_EXISTING);
                }
                catch (IOException IOE){
                    System.out.println(IOE.getMessage());
                }
            }
            bookService.update(Long.valueOf(id),book);
        } catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable String id, Model model){
        try {
            bookService.deleteById(Long.valueOf(id));
            return "redirect:/";
        } catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/";
    }

}

