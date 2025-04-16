package moon.bitter.Mapper;


import moon.bitter.Pojo.Movie;
import moon.bitter.Pojo.Region;
import moon.bitter.Pojo.Type;
import moon.bitter.Utils.Result;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.Map;

@Mapper
public interface MovieMapper {
    @Select("select * from movie_info limit 10")
    ArrayList<Map<String, Movie>> getInfo();

    @Insert("insert into movie_info(id , title)" +
            " values(#{id} , #{title})")
    void addMovie(@Param("id") String id, @Param("title") String title);

    @Delete("delete from movie_info where id = #{id}")
    void deleteMovie(@Param("id") String id);

    @Update("update movie_info set title = #{m.title}  ," +
            "year = #{m.year} , " +
            "region= #{m.region} , " +
            "type = #{m.type} ," +
            "director = #{m.director} ," +
            "ctors = #{m.ctors} , " +
            "count = #{m.count} ," +
            "score = #{m.score} ," +
            "star = #{m.star} , " +
            "url = #{m.url}" +
            "where id = #{m.id}")
    void updateMovie(@Param("m") Movie movie);

    @Select("<script>"
            + "SELECT * FROM movie_info "
            + "WHERE 1=1 "
            + "<if test='title != null and title != \"\"'>"
            + "AND title LIKE CONCAT('%', #{title}, '%') "
            + "</if>"
            + "<if test='region != null and region != \"\"'>"
            + "AND region LIKE CONCAT('%', #{region}, '%') "
            + "</if>"
            + "<if test='type != null and type != \"\"'>"
            + "AND type LIKE CONCAT('%', #{type}, '%') "
            + "</if>"
            + "</script>")
    ArrayList<Map<String, Movie>> keywordByMovies(@Param("title") String title,
                                                  @Param("region") String region,
                                                  @Param("type") String type);

    @Select("select * from region")
    ArrayList<Map<String, Region>> getRegion();

    @Select("select * from type")
    ArrayList<Map<String, Type>> getType();

    @Select("select count(*) from movie_info")
    Integer getMovieCount();

    @Select("select * from movie_info limit #{start} offset #{end}")
    ArrayList<Map<String, Movie>> getMovieList(int start, int end);
}
