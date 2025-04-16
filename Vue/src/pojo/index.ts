import {ref} from "vue"

export interface Movie {
    id? : string ,
    title? :  string ,
    year? : string ,
    region? : string ,
    type? : string ,
    director? : string ,
    ctors? : string ,
    count? : string ,
    score? : string ,
    star? : string ,
    url? : string 
}

export interface Region {
    id? : string , 
    name? : string 
}

export interface Type {
    id? : string , 
    name? : string 
}

export interface MvPage{
    size : number ,
    page : number
}