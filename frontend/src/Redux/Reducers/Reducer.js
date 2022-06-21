import { ActionTypes } from "../constant"


const initialState={
    
    Product:[],
    client:[]

}




export const productReducer=(state=initialState,{type,payload})=>
{
    switch (type){
        case ActionTypes.SET_PRODUCTS:
            return {...state, Product:payload }

        case ActionTypes.SELECTED_PRODUCTS:
            return {...state, Product:payload }
      
      
        case ActionTypes.SELECTED_CLIENT:
            return {...state, client:payload }
        

        default :
            return state
    }
}