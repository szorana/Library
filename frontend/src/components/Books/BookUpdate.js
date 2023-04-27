import React from 'react';
import {useHistory} from 'react-router-dom';

const BookUpdate = (props) => {

    const history = useHistory();
    const [formData, updateFormData] = React.useState({
        name: "",
        category: "",
        authorID: 0,
        availableCopies: 0
    });

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim(),
        });
    };

    const onFormSubmit = (e) => {
        e.preventDefault();
        const name = formData.name!== "" ? formData.name : props.book.name;
        const category = formData.category.toString() !== "" ? formData.category : props.book.category;
        const authorID = formData.authorID!== 0 ? formData.authorID : props.book.authorID.id;
        const availableCopies = formData.availableCopies !== 0 ? formData.availableCopies : props.book.availableCopies;
        props.onEditBook(props.book.id,name, category, authorID, availableCopies);
        history.push("/books");
    };

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
                               placeholder={props.book.name}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label>Category</label>
                        <select name="category" className="form-control" onChange={handleChange}>
                            {props.categories.map( (term) => {
                                if(props.book.category !== undefined &&
                                    props.book.category === term)
                                    return <option selected={props.book.category}
                                                   value={term.toString()}
                                                   key={term.toString()}> {term.toString()}</option>
                                else return <option value={term.toString()} key={term.toString()}> {term.toString()}</option>
                            })

                            }
                        </select>
                    </div>
                    <div className="form-group">
                        <label>Author</label>
                        <select name="authorID" className="form-control" onChange={handleChange}>
                            {props.authors.map( (term) => {
                                if(props.book.authorID !== undefined &&
                                props.book.authorID.id === term.id)
                                    return <option selected={props.book.authorID.id}
                                                    value={term.id}
                                                    key={term.id}> {term.name}</option>
                                else return <option value={term.id} key={term.id}> {term.name}</option>
                            })

                            }
                        </select>
                    </div>
                    <div className="form-group">
                        <label htmlFor="quantity">Available Copies</label>
                        <input type="number"
                               className="form-control"
                               id="availableCopies"
                               name="availableCopies"
                               placeholder={props.book.availableCopies}
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

export default BookUpdate;
