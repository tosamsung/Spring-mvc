app.controller("product-ctrl", function($scope, $http) {
	$scope.items = [];
	$scope.categories = [];
	$scope.form = {
		createDate: new Date(),
		image: 'cloud-upload.jpg',
		available: true
	};


	$scope.initialize = function() {
		$http.get("/rest/products").then(resp => {
			$scope.items = resp.data;
			$scope.items.forEach(item => {
				item.createDate = new Date(item.createDate)
			})
		});

		$http.get("/rest/categories").then(resp => {
			$scope.categories = resp.data;
		})
	}
	$scope.initialize();
	
	$scope.create = function(){
		var item = angular.copy($scope.form);
		$http.post('/rest/products', item).then(resp => {
			resp.data.createDate = new Date(resp.data.createDate)
			$scope.items.push(resp.data);
			$scope.reset();
			alert('Create new product successfuly');
		}).catch(Error => {
			alert('Error create new product');
		})
	}
	
	$scope.update = function(){
		var item = angular.copy($scope.form);
		$http.put(`/rest/products/${item.id}`, item).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items[index] = item;
			alert('Update product successfuly');
		}).catch(Error => {
			alert('Error update product');
		})
	}
	
	$scope.delete = function(item){
		$http.delete(`/rest/products/${item.id}`).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items.splice(index, 1);
			$scope.reset();
			alert('Delete product successfuly');
		}).catch(Error => {
			alert('Error delete product');
		})
	}

	$scope.edit = function(item) {
		$scope.form = angular.copy(item);
	};

	$scope.reset = function() {
		$scope.form = {
			createDate: new Date(),
			image: 'cloud-upload.jpg',
			available: true
		};
	}

	$scope.imageChanged = function(files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/images', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.image = resp.data.name; // Cập nhật form.image với tên ảnh từ phản hồi
		}).catch(error => {
			alert('Lỗi upload hình ảnh');
			console.log("Error", error);
		});
	};

	$scope.pager = {
        page: 0,
        size: 5,
        get items(){
            var start = this.page * this.size;
            return $scope.items.slice(start, start + this.size);
        },
        get pageCount(){
            return Math.ceil(1.0 * $scope.items.length / this.size);
        },
        first(){
            this.page = 0;
        },
        prev(){
            if(this.page > 0){
                this.page--;
            }
        },
        next(){
            if(this.page < this.pageCount - 1){
                this.page++;
            }
        },
        last(){
            this.page = this.pageCount - 1;
        }
    };
});