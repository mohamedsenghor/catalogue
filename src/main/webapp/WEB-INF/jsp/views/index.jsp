<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!doctype html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Catalogue</title>
</head>
<body>
<h1>Gestion de Catalogue</h1>
<div>
  <form id="productForm">
    <div>
      <p id="reference-error" class="post_validation_error" style="color: red;"></p>
      <label for="reference">Reference</label>
      <input type="text" id="reference" required>
    </div>
    <div>
      <p id="designation-error" class="post_validation_error" style="color: red;"></p>
      <label for="designation">Designation</label>
      <input type="text" id="designation" required>
    </div>
    <div>
      <p id="price-error" class="post_validation_error" style="color: red;"></p>
      <label for="price">Price</label>
      <input type="text" id="price" required>
    </div>
    <div>
      <p id="quantity-error" class="post_validation_error" style="color: red;"></p>
      <label for="quantity">Quantity</label>
      <input type="text" id="quantity" required>
    </div>

    <div>
      <button type="button" id="saveProductButton">Save</button>
    </div>
  </form>
</div>

<div>
  <table id="productsTable" style="display: none;">
    <thead>
    <tr>
      <th>Reference</th>
      <th>Designation</th>
      <th>Price</th>
      <th>Quantity</th>
    </tr>
    </thead>
    <tbody>
    </tbody>
  </table>
</div>

<script>
  let currentProductReference = null;
  function fetchAllProducts() {
    fetch('/api/products')
      .then(response => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        console.log('Got a response', response);
        return response.json();
      })
      .then(products => {
        const tableBody = document.querySelector('#productsTable tbody');
        tableBody.innerHTML = ''; // Clear existing rows

        products.forEach(product => {
          const row = tableBody.insertRow();
          row.insertCell(0).textContent = product.reference;
          row.insertCell(1).textContent = product.designation;
          row.insertCell(2).textContent = product.price;
          row.insertCell(3).textContent = product.quantity;
          const actionsCell = row.insertCell(4);

          const viewButton = document.createElement('button');
          viewButton.innerHTML = 'View';
          viewButton.addEventListener('click', () => viewProduct(product.reference));
          actionsCell.appendChild(viewButton);

          const deleteButton = document.createElement('button');
          deleteButton.innerHTML = 'Delete';
          deleteButton.addEventListener('click', () => deleteProduct(product.reference));
          actionsCell.appendChild(deleteButton);

          const modifyButton = document.createElement('button');
          modifyButton.innerHTML = 'Modify';
          modifyButton.addEventListener('click', () => modifyProduct(product));
          actionsCell.appendChild(modifyButton);
        });
        document.getElementById("productsTable").style.display = "table";
      })
      .catch(error => console.error('Error:', error));
  }

  function viewProduct(reference) {
    fetch('/api/products/' + reference)
      .then(response => {
        if (!response.ok) {
          throw new Error('Product not found');
        }
        return response.json();
      })
      .then(product => {
        alert("Reference: " + product.reference +
          "\nDesignation: " + product.designation +
          "\nPrice: " + product.price +
          "\nQuantity: " + product.quantity
        );
      })
      .catch(error => alert(error.message));
  }

  function modifyProduct(product) {
    document.getElementById('reference').value = product.reference;
    document.getElementById('designation').value = product.designation;
    document.getElementById('price').value = product.price;
    document.getElementById('quantity').value = product.quantity;
    currentProductReference = product.reference;
  }

  function deleteProduct(reference) {
    fetch('/api/products/' + reference, {
      method: 'DELETE'
    })
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.text();
      })
      .then(message => {
        alert(message);
        fetchAllProducts();
      })
      .catch(error => alert(error.message));
  }

  document.addEventListener('DOMContentLoaded', fetchAllProducts);

  document.getElementById("saveProductButton").addEventListener('click', function () {
    const form = document.getElementById("productForm");
    const product = {
      reference: form.reference.value,
      designation: form.designation.value,
      price: parseFloat(form.price.value),
      quantity: parseInt(form.quantity.value)
    };

    const fetchMethod = currentProductReference ? "PUT" : "POST";
    const fetchURL = currentProductReference ? "/api/products/" + currentProductReference :"/api/products/";

    fetch(fetchURL, {
      method: fetchMethod,
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(product)
    })
      .then(async response => {
        const data = await response.json();
        if (!response.ok) {
          throw new Error(JSON.stringify(data));
        }
        return data;
      })
      .then(result => {
        console.log(result);
        const validatorEls = document.querySelectorAll('.post_validation_error');
        validatorEls.forEach((el) => {
          el.textContent = '';
        });
        form.reset();
        fetchAllProducts();
      })
      .catch(error => {
        console.log(error.message);
        const validationErrors = JSON.parse(error.message);
        console.log("The parsed error", validationErrors);
        Object.entries(validationErrors).forEach(([key, value]) => {
          const field = key.slice(0, key.length - 5)
          const validationErrorEl = document.getElementById(field + "-error");
          if (value)
            validationErrorEl.textContent = value?.toString() || "";
          else
            validationErrorEl.textContent = "";
        });
      });
  });
</script>
</body>
</html>
