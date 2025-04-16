package moon.bitter.Controller;

import moon.bitter.Pojo.Movie;
import moon.bitter.Pojo.Page;
import moon.bitter.Pojo.Region;
import moon.bitter.Pojo.Type;
import moon.bitter.Service.MovieService;
import moon.bitter.Utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movieInfo")
    public Result<ArrayList<Map<String , Movie>>> getMovieInfo(){
        return movieService.getInfo() ;
    }

    @PostMapping("movieList")
    public Result<ArrayList<Map<String , Movie>>> getMovieList(@RequestBody Page page ){
        return movieService.getMovieList(page) ;
    }

    @PostMapping("/addMovie")
    public Result addMovie(@RequestBody Movie movie){
        return movieService.addMovie(movie) ;
    }

    @PostMapping("deleteMovie")
    public Result deleteMovie(@RequestBody Movie movie){
        return movieService.deleteMovie(movie) ;
    }

    @PostMapping("updateMovie")
    public Result updateMovie(@RequestBody Movie movie){
        System.out.println(movie);
        return movieService.updateMovie(movie) ;
    }

    @PostMapping("searchMovie")
    public Result searchMovie(@RequestBody Page page){
//        System.out.println(page);
        return movieService.searchMovie(page) ;
    }
    @GetMapping("regionList")
    public Result<ArrayList<Map<String , Region>>> getRegion(){
        return movieService.getRegion() ;
    }

    @GetMapping("typeList")
    public Result<ArrayList<Map<String , Type>>> getType(){
        return movieService.getType() ;
    }

    @GetMapping("movieCount")
    public Result movieCount(){
        return movieService.getMovieCount() ;
    }

}
