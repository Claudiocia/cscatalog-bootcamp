import React from "react";
import './styles.scss';
import ProductCard from "./components/ProductCard";

const Catalog = () => (
    <div className="catalog-container">
        <div className="row">
            <h6 className="title-catalog col-3">Catálogo de produtos</h6>
            <div className="box-search col-9">
                <h6 className="area-filtro"> área dos filtros </h6>
            </div>
        </div>
        <div className="catalog-products">
            <ProductCard />
            <ProductCard />
            <ProductCard />
            <ProductCard />
            <ProductCard />
            <ProductCard />
        </div>
    </div>
);

export default Catalog;