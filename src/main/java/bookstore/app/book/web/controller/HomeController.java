package bookstore.app.book.web.controller;

import bookstore.app.book.dto.BookDto;
import bookstore.app.book.service.IBookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class HomeController {

    private final IBookService bookService;

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

        List<BookDto> listBookDto = (List<BookDto>) bookService.getAll(pageable);
        int total = bookService.getAll().size();
        int totalPages = total % limit != 0
                ? (int) Math.ceil(total / limit)
                : total / limit -1;
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("pageNumber",currentPage);
        model.addAttribute("limit",limit);
        model.addAttribute("sort_by",sortBy);
        model.addAttribute("type_sort",typeSort);
        model.addAttribute("books",listBookDto);
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
}
