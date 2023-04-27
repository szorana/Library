import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router, Redirect, Route} from 'react-router-dom'
import BookList from "../Books/BookList";
import BookUpdate from "../Books/BookUpdate";
import LibraryService from "../../repository/libraryRepository";
import Authors from "../Authors/Authors";
import BookAdd from "../Books/BookAdd";
import Header from "../Header/header";
import Countries from "../Countries/Countries";
import Categories from "../Categories/Categories";


class App extends Component{

    constructor(props) {
        super(props);
        this.state = {
            authors: [],
            countries: [],
            books: [],
            categories: [],
            selectedBook: {}
        }
    }
    render() {
        return (
            <Router>
                <Header/>
                <main>
                    <div className="container">
                        <Route path={"/authors"} exact render={() =>
                            <Authors authors={this.state.authors}/>}/>
                        <Route path={"/books/addNewBook"} exact render={() =>
                            <BookAdd categories={this.state.categories}
                                     authors={this.state.authors}
                                     onAddBook={this.addBook}/>}/>
                        <Route path={"/books/edit/:id"} exact render={() =>
                            <BookUpdate categories={this.state.categories}
                                        authors={this.state.authors}
                                        onEditBook={this.editBook}
                                        book={this.state.selectedBook}/>}/>
                        <Route path={"/books"} exact render={() =>
                            <BookList books={this.state.books}
                                      onDelete={this.deleteBook}
                                      onEdit={this.getBook}
                                      onMark={this.markAsTaken}/>}/>
                        <Route path={"/countries"} exact render={() =>
                            <Countries countries={this.state.countries}/>}/>
                        <Route path={"/categories"} exact render={() =>
                            <Categories categories={this.state.categories}/>}/>
                        <Route path={"/"}  book={<Redirect replace to="/books"/>}/>
                    </div>
                </main>
            </Router>
        );
    }

    componentDidMount() {
        this.fetchData()
    }

    fetchData = () => {
        this.loadAuthors();
        this.loadBooks();
        this.loadCountries();
        this.loadCategories();

    }

    loadAuthors = () => {
        LibraryService.fetchAuthors()
            .then((data) => {
                this.setState({
                    authors: data.data
                })
            });
    }

    loadBooks = () => {
        LibraryService.fetchBooks()
            .then((data) => {
                this.setState({
                    books: data.data
                })
            });
    }

    loadCountries = () => {
        LibraryService.fetchCountries()
            .then((data) => {
                this.setState({
                    countries: data.data
                })
            });
    }

    loadCategories = () => {
        LibraryService.fetchCategories()
            .then((data) => {
                this.setState({
                    categories: data.data
                })
            });
    }

    deleteBook = (id) => {
        LibraryService.deleteBook(id)
            .then(() => {
                this.loadBooks();
            });
    }

    addBook = (name, category, author, availableCopies) => {
        LibraryService.addBook(name, category, author, availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }

    getBook = (id) => {
        LibraryService.getBook(id)
            .then((data) => {
                this.setState({
                    selectedBook: data.data
                })
            })
    }

    editBook = (id, name, category, author, availableCopies) => {
        LibraryService.updateBook(id, name, category, author, availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }

    markAsTaken = (id) => {
        LibraryService.markAsTaken(id)
            .then( ()=>{
                this.loadBooks();
            });
    }
}

export default App;
