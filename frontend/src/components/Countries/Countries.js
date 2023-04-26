import React from "react";

const Countries=(props)=>{
    return(
        <div>
            <div className={"container mm-4 mt-5"}>
                <div className={"row"}>
                    <div className={"row"}>
                        <table className={"table table-stripped"}>
                            <thead>
                            <tr>
                                <th scope={"col"}>Name</th>
                                <th scope={"col"}>Continent</th>
                            </tr>
                            </thead>
                            <tbody>
                            {props.countries.map((term) => {
                                return (
                                    <tr>
                                        <td>{term.name}</td>
                                        <td>{term.continent}</td>

                                    </tr>
                                );
                            })}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Countries;