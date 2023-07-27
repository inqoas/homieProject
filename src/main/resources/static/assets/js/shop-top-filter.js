 
$(function(){      
        const products =document.querySelector("#product");

		//查出全部商品數量
		// $.ajax({
		// 	url : "/homieProject/product/ProductFindCountController",
		// 	type : "GET",
		// 	dataType : "json",
		// 	success : function(data) {
	
		// 		fruit_number.innerHTML="("+data[0]+")";
		// 		cake_number.innerHTML="("+data[1]+")";
		// 		behe_number.innerHTML="("+data[2]+")";
		// 		snacks_number.innerHTML="("+data[3]+")";
	
		// }});

		All_product();

		function All_product(){
		//全部商品列	
			$.ajax({
			url : "/homieProject/product/findall",
			type : "GET",
			dataType : "json",
			success : function(data) {
		
					for ( let key in data) {
						
					// 創建一個<div>元素來代表產品展示卡片
					const productCard = document.createElement("div");
					productCard.className = "product-box-3 h-100 wow fadeInUp";
				
					// 創建產品的header部分
					const productHeader = document.createElement("div");
					productHeader.className = "product-header";
					const productImage = document.createElement("div");
					productImage.className = "product-image";
					const productImageLink = document.createElement("a");
					productImageLink.href = "product-left-thumbnail.html";
					const productImg = document.createElement("img");

					productImg.id ="product_img"
					productImg.src ="http://localhost:8080/homieProject/product/ProductFindImgController?product_id="+`${data[key].product_id}`;
					productImg.className = "img-fluid blur-up lazyload";
					productImg.alt = "";
				
					// 把元素組合在一起
					productImageLink.appendChild(productImg);
					productImage.appendChild(productImageLink);
					productHeader.appendChild(productImage);
					productCard.appendChild(productHeader);
				
					// 創建產品的footer部分
					const productFooter = document.createElement("div");
					productFooter.className = "product-footer";
					const productDetail = document.createElement("div");
					productDetail.className = "product-detail";
				
					// 添加產品詳細資訊
					const productName = document.createElement("h5");
					const productNameLink = document.createElement("a");
					productNameLink.href = "product-left-thumbnail.html";
					productNameLink.innerText = `${data[key].product_name}`;
					productName.appendChild(productNameLink);
				
					const productDescription = document.createElement("p");
					productDescription.className = "text-content mt-1 mb-2 product-content";
					productDescription.innerText = "asdsadsads";
				
					const productRating = document.createElement("div");
					productRating.className = "product-rating mt-2";
					const ratingList = document.createElement("ul");
					ratingList.className = "rating";
				
					for (let i = 0; i < 5; i++) {

						const star   = document.createElement("li");
						const  svg   = document.createElement("svg");
						const  ii    = document.createElement("i");
						const polygon= document.createElement("polygon");
					

						if( i < data[key].product_review_stars ){

							ii.style="color:yellow";
							ii.className="bi bi-star-fill";
						
							star.appendChild(ii);
						
						}else{

						
							ii.className="bi bi-star";
						
							star.appendChild(ii);
						}
									
						ratingList.appendChild(star);
					}
				
					const ratingValue = document.createElement("span");
					ratingValue.innerText = "(" + data[key].product_review_stars + ")";
				
					productRating.appendChild(ratingList);
					productRating.appendChild(ratingValue);
				
					const productUnit = document.createElement("h6");
					productUnit.className = "unit";
					productUnit.innerText ="$"+ data[key].product_price;
				
					const productPrice = document.createElement("h5");
				// productPrice.innerHTML = '<span class="theme-color">' + "sdsdsd" + '</span> <del>' + "sdsdd" + '</del>';
				
					// 把元素組合在一起
					productDetail.appendChild(productName);
					productDetail.appendChild(productDescription);
					productDetail.appendChild(productRating);
					productDetail.appendChild(productUnit);
					productDetail.appendChild(productPrice);
					productFooter.appendChild(productDetail);
					productCard.appendChild(productFooter);
				
					// 把產品展示卡片插入到容器中
					products.appendChild(productCard);
				

					}

				}
			})
		
		}	
		

    })