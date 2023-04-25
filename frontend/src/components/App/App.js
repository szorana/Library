import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router, Redirect, Route} from 'react-router-dom'


function App() {
    return (
        <Router>
            <main>
                <div className="container">
                    <Route path={"/manufacturers"} exact render={() =>
                        <Manufacturers manufacturers={this.state.manufacturers}/>}/>
                    <Route path={"/categories"} exact render={() =>
                        <Categories categories={this.state.categories}/>}/>
                    <Route path={"/products/add"} exact render={() =>
                        <ProductAdd categories={this.state.categories}
                                    manufacturers={this.state.manufacturers}
                                    onAddProduct={this.addProduct}/>}/>
                    <Route path={"/products/edit/:id"} exact render={() =>
                        <ProductEdit categories={this.state.categories}
                                     manufacturers={this.state.manufacturers}
                                     onEditProduct={this.editProduct}
                                     product={this.state.selectedProduct}/>}/>
                    <Route path={"/products"} exact render={() =>
                        <Products products={this.state.products}
                                  onDelete={this.deleteProduct}
                                  onEdit={this.getProduct}/>}/>
                    <Route path={"/login"} exact render={() => <Login onLogin={this.fetchData}/>}/>
                    <Redirect to={"/products"}/>
                </div>
            </main>
        </Router>

    );
}

export default App;
