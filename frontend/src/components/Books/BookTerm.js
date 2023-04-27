import React from 'react';
import {Link} from 'react-router-dom';

const BookTerm = (props) => {
    return (
        <tr key={props.book.id}>
            <td>{props.book.name}</td>
            <td>{props.book.category}</td>
            <td>{props.book.authorID}</td>
            <td>{props.book.availableCopies}</td>
            <td scope={"col"} className={"text-right"}>
                <a title={"Delete"} className={"btn btn-danger"}
                   onClick={() => props.onDelete(props.book.id)}>
                    Delete
                </a>
                <Link className={"btn btn-info ml-2"}
                      onClick={() => props.onEdit(props.book.id)}
                      to={`/books/edit/${props.book.id}`}>
                    Edit
                </Link>
                <a className={"btn btn-secondary ml-2"}
                   onClick={() => props.onMark(props.book.id)}>Mark As Taken</a>
            </td>
        </tr>
    )
}

export default BookTerm;