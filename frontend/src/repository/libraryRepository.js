import axios from "../custom-axios/axios";

const LibraryService= {
    fetchBooks: () => {
        return axios.get("/books");
    },
    getBook: (id) => {
      return  axios.get(`/books/${id}`);
    },
    addBook: (name, category, author, availableCopies) => {
        return axios.post("/books/addNewBook", {
            "name": name,
            "category": category,
            "author": author,
            "availableCopies": availableCopies
        });
    },
    updateBook: (id, name, category, author, availableCopies) => {
        return axios.put(`/books/edit/${id}`, {
            "name": name,
            "category": category,
            "author": author,
            "availableCopies": availableCopies
        });
    },
    deleteBook: (id) => {
        return axios.delete(`/books/delete/${id}`);
    },
    markAsTaken: (id) => {
        return axios.put(`/books/${id}/taken`)
    },
    fetchAuthors: () => {
        return axios.get("/authors");
    },
    fetchCountries: () => {
        return axios.get("/countries")
    },
    fetchCategories: () => {
        return axios.get("/categories")
    }
}

export default LibraryService;