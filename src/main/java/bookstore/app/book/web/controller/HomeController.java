package bookstore.app.book.web.controller;

import bookstore.app.book.dto.BookDto;
import bookstore.app.book.dto.CategoryDto;
import bookstore.app.book.entity.Category;
import bookstore.app.book.service.IBookService;
import bookstore.app.book.service.ICategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class HomeController {

    private final IBookService bookService;
    private final ICategoryService categoryService;

    @GetMapping("")
    public String getHome(Model model,
                          @RequestParam(name = "pageNumber",required = false) String pageNumber,
                          @RequestParam(name = "limitParam", required = false) String limitParam,
                          @RequestParam(name = "sort_by", required = false, defaultValue = "id") String sortBy,
                          @RequestParam(name = "type_sort", required = false,defaultValue = "ASC") String typeSort){
        int currentPage = pageNumber == null ? 0 : Integer.valueOf(pageNumber);
        int limit = limitParam == null ? 15 : Integer.valueOf(limitParam);

//        if(sortBy != null && typeSort!= null && pageNumber == null) currentPage = 1;

        Sort sort;
        if(typeSort.equals("ASC")){
            sort = Sort.by(Sort.Direction.ASC,sortBy);
        }else{
            sort = Sort.by(Sort.Direction.DESC,sortBy);
        }
        Pageable pageable = PageRequest.of(currentPage,limit,sort);

        Page<BookDto> listBookDto = (Page<BookDto>) bookService.getAll(pageable);
//        int total = bookService.getAll().size();
//        int totalPages = total % limit != 0
//                ? (int) Math.ceil(total / limit)
//                : total / limit -1;
        System.out.println(listBookDto.getTotalPages());
        model.addAttribute("totalPages",listBookDto.getTotalPages());
        model.addAttribute("pageNumber",currentPage);
        model.addAttribute("limit",limit);
        model.addAttribute("sort_by",sortBy);
        model.addAttribute("type_sort",typeSort);
        model.addAttribute("books",listBookDto);

        List<CategoryDto> category = (List<CategoryDto>) categoryService.getAll();
        model.addAttribute("category",category);
        System.out.println(currentPage);
        return "home";
    }

//    @GetMapping("/sort")
//    public String getSortHome(Model model,
//                              @RequestParam("sort_by") String sortBy,
//                              @RequestParam("type_sort") String typeSort
//    ){
//        List<BookDto> listBookDto = bookService.getAllBookBySort(sortBy,typeSort);
//        model.addAttribute("books",listBookDto);
//        return "home";
//    }

    @GetMapping("/search/{search}")
    public String findBook(Model model,
                           @PathVariable("search") String search,
                           @RequestParam(name = "type_sort", required = false) String typeSort
    ){
        List<CategoryDto> category = (List<CategoryDto>) categoryService.getAll();
        model.addAttribute("category",category);

        List<BookDto> listBookDto = (List<BookDto>) bookService.searchBook(search);
        model.addAttribute("totalPages",1);
        model.addAttribute("pageNumber",0);

        if("ASC".equals(typeSort)){
            listBookDto.sort(
                    (o1,o2) -> o1.getPrice().compareTo(o2.getPrice())
            );
        }
        if("DESC".equals(typeSort)){
            listBookDto.sort(
                    (o1,o2) -> o2.getPrice().compareTo(o1.getPrice())
            );
        }

        model.addAttribute("books",listBookDto);
        return "home";
    }

    @GetMapping("/category/{id}")
    public String getBookByCategory(Model model,
                                    @PathVariable String id,
                                    @RequestParam(name = "sort_by", required = false) String sortBy,
                                    @RequestParam(name = "type_sort", required = false) String typeSort
        ){
        List<CategoryDto> category = (List<CategoryDto>) categoryService.getAll();
        model.addAttribute("category",category);
        List<BookDto> listBookDto = (List<BookDto>) bookService.getByCategory(id);
        model.addAttribute("totalPages",1);
        model.addAttribute("pageNumber",0);
        if("ASC".equals(typeSort)){
            listBookDto.sort(
                    (o1,o2) -> o1.getPrice().compareTo(o2.getPrice())
            );
        }
        if("DESC".equals(typeSort)){
            listBookDto.sort(
                    (o1,o2) -> o2.getPrice().compareTo(o1.getPrice())
            );
        }
        model.addAttribute("books",listBookDto);
        return "home";
    }

}
