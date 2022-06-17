import { ActionTypes } from "../constant"


const initialState={
    
    Product:[]

}


export const productReducer=(state=initialState,{type,payload})=>
{
    switch (type){
        case ActionTypes.SET_PRODUCTS:
            return {...state, Product:payload }

        case ActionTypes.SELECTED_PRODUCTS:
            return {...state, Product:payload }

        default :
            return state
    }
}