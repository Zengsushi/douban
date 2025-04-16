package moon.bitter.Service.impl;

import cn.hutool.json.JSONUtil;
import moon.bitter.Config.SpringbootKafkaConfig;
import moon.bitter.Mapper.MovieMapper;
import moon.bitter.Pojo.Movie;
import moon.bitter.Pojo.Page;
import moon.bitter.Pojo.Region;
import moon.bitter.Pojo.Type;
import moon.bitter.Service.MovieService;
import moon.bitter.Utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Map;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public Result<ArrayList<Map<String, Movie>>> getInfo() {
        ArrayList<Map<String, Movie>> list = movieMapper.getInfo();
        return Result.success(list);
    }

    @Override
    public Result addMovie(Movie movie) {
        try {
            // 新增电影 日志
            String obj2String = JSONUtil.toJsonStr(movie);
            kafkaTemplate.send(SpringbootKafkaConfig.TOPIC_ADD_MOVIE_LOG, obj2String);
            movieMapper.addMovie(movie.getId(), movie.getTitle());
        } finally {
            return Result.success();
        }
    }

    @Override
    public Result deleteMovie(Movie movie) {
        try {
            movieMapper.deleteMovie(movie.getId());
            return Result.success();
        } catch (Exception e) {
            return Result.error("删除失败");
        }
    }

    @Override
    public Result updateMovie(Movie movie) {
        try {
            movieMapper.updateMovie(movie);
            return Result.success();
        } catch (Exception e) {
            return Result.error("修改失败");
        }
    }

    @Override
    public Result searchMovie(Page page) {
        try {
            ArrayList<Map<String, Movie>> list = movieMapper.keywordByMovies(page.getTitle() , page.getRegion() ,
            page.getType());
            return Result.success(list) ;
        } catch (Exception e) {
            return Result.error("响应异常!");
        }
    }

    @Override
    public Result<ArrayList<Map<String, Region>>> getRegion() {
        try {
            ArrayList<Map<String, Region>> list = movieMapper.getRegion();
            return Result.success(list) ;
        }catch (Exception e) {
            return Result.error("响应异常!") ;
        }
    }

    @Override
    public Result<ArrayList<Map<String, Type>>> getType() {
        try {
            ArrayList<Map<String, Type>> list = movieMapper.getType();
            return Result.success(list) ;
        }catch (Exception e) {
            return Result.error("响应异常!") ;
        }
    }

    @Override
    public Result getMovieCount() {
        Integer cnt = movieMapper.getMovieCount() ;
        return Result.success(cnt) ;
    }

    // 分页查询
    @Override
    public Result<ArrayList<Map<String, Movie>>> getMovieList(Page page) {
        int start = (page.getPage() - 1) * page.getSize()  ;
        ArrayList<Map<String , Movie>> list = movieMapper.getMovieList(page.getSize() , start) ;
        return Result.success(list) ;
    }


}
