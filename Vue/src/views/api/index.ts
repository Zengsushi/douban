import instance  from "@/utils/result";
import { type Movie } from "@/pojo";

export const AdminLogin = (data:any) => {    
    return  instance.post("/user/adminLogin", data )
}

export const UserLogin = (data:any) => {
    return instance.post("/user/login" , data)
}

export const UesrRegister = (data:any) => {
    return instance.post("/user/adminRegister", data)
}

export const MovieList = (page: any) => {
    console.log(page);
    return instance.post("movie/movieList" , page)
}

export const MovieDetail = (data:Movie) => {
    return instance.post("/movie/deleteMovie" , data)
}

export const searchMovieInfo = (data:Movie) => {
    return instance.post("/movie/searchMovie" , data)
}

export const getRegionList = () => {
    return instance.get("/movie/regionList")
}

export const getTypeList = () => {
    return instance.get("/movie/typeList")
}

export const getMovieCount = () => {
    return instance.get("/movie/movieCount")
}

export const updateMovieInfo = (movie:Movie) => {
    return instance.post("/movie/updateMovie" , movie)
}