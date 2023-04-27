import React from "react";
import {useHistory} from "react-router-dom";

const BookAdd = (props) => {

    const history = useHistory();
    const [formData, updateFormData] = React.useState({
        name: "",
        category: "",
        author: 1,
        availableCopies: 1
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    /*const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.name === "author" ? e.target.value : e.target.value.trim()
        });
    }*/


    const onFormSubmit = (e) => {
        e.preventDefault();
        const name = formData.name;
        const category = formData.category;
        const author = formData.author;
        const availableCopies = formData.availableCopies;

        props.onAddBook(name, category, author, availableCopies);
        history.push("/books");
    }

    return(
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="name">Book Title</label>
                        <input type="text"
                               className="form-control"
                               id="name"
                               name="name"
                               required
                               placeholder="Enter book title"
                               onChange={handleChange}
                        />
                    </div>
                    <br/>
                    <div className="form-group">
                        <label>Category</label>
                        <select
                            name="category"
                            id="category"
                            className="form-control"
                            onChange={handleChange}
                        >
                            {props.categories.map((book) => (
                                <option key={book} value={book}>
                                    {book}
                                </option>
                            ))}
                        </select>
                    </div>
                    <br/>
                    <div className="form-group">
                        <label>Author</label>
                        <select name="author"
                                className="form-control"
                                onChange={handleChange}>
                            {props.authors.map((term) =>
                                <option key={term.id} value={term.id}>{term.name}</option>
                            )}
                        </select>
                    </div>
                    <br/>
                    <div className="form-group">
                        <label htmlFor="availableCopies">Available copies</label>
                        <input
                            type="number"
                            className="form-control"
                            id="availableCopies"
                            name="availableCopies"
                            placeholder="100"
                            disabled
                            onChange={handleChange}
                        />
                    </div>
                    <br/>
                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    )
}

export default BookAdd;