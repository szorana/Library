import React from "react";
import {useHistory} from "react-router-dom";

const BookAdd = (props) => {

    const history = useHistory();
    const [formData, updateFormData] = React.useState({
        name: "",
        category: "",
        authorID: 1,
        availableCopies: 0
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const name = formData.name;
        const category = formData.category;
        const authorID = formData.authorID;
        const availableCopies = formData.availableCopies;

        props.onAddBook(name, category, authorID, availableCopies);
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
                    <div className="form-group">
                        <label htmlFor="availableCopies">Available copies</label>
                        <input
                            type="number"
                            className="form-control"
                            id="availableCopies"
                            name="availableCopies"
                            placeholder="Enter available copies"
                            required
                            onChange={handleChange}
                        />
                    </div>
                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    )
}

export default BookAdd;