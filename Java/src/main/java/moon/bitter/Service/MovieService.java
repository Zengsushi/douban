package moon.bitter.Service;

import moon.bitter.Pojo.Movie;
import moon.bitter.Pojo.Page;
import moon.bitter.Pojo.Region;
import moon.bitter.Pojo.Type;
import moon.bitter.Utils.Result;

import java.util.ArrayList;
import java.util.Map;

public interface MovieService {

    Result<ArrayList<Map<String, Movie>>> getInfo();

    Result addMovie(Movie movie) ;

    Result deleteMovie(Movie movie);

    Result updateMovie(Movie movie);

    Result searchMovie(Page page);

    Result<ArrayList<Map<String, Region>>> getRegion();

    Result<ArrayList<Map<String, Type>>> getType();

    Result getMovieCount();

    Result<ArrayList<Map<String, Movie>>> getMovieList(Page page);
}
