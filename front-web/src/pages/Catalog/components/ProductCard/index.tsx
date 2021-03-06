import React from "react";
import { ReactComponent as ProductImage  } from '../../../../core/assets/images/product.svg';
import './styles.scss';
const ProductCard = () => (
    <div className="card-base border-radius-10 product-card">
        <ProductImage />
        <div className="product-info">
            <h6 className="product-name">Computador Desktop - Intel Core i7</h6>
            <div className="row div-price">
                <span className="real-symbol">R$</span>
                <h3 className="product-price">2.779,00</h3>
            </div>
        </div>
    </div>
);

export default ProductCard;