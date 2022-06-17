import { combineReducers } from "redux";
import { productReducer } from "./Reducer";



export const reducers=combineReducers({

    allProducts:productReducer

})