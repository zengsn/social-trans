var DataSourceTree = function(options) {
	this._data 	= options.data;
	this._delay = options.delay;
}

DataSourceTree.prototype.data = function(options, callback) {
	var self = this;
	var $data = null;

	if(!("name" in options) && !("type" in options)){
		$data = this._data;//the root tree
		callback({ data: $data });
		return;
	}
	else if("type" in options && options.type == "folder") {
		if("additionalParameters" in options && "children" in options.additionalParameters)
			$data = options.additionalParameters.children;
		else $data = {}//no data
	}
	
	if($data != null)//this setTimeout is only for mimicking some random delay
		setTimeout(function(){callback({ data: $data });} , parseInt(Math.random() * 500) + 200);

	//we have used static data here
	//but you can retrieve your data dynamically from a server using ajax call
	//checkout examples/treeview.html and examples/treeview.js for more info
};

var tree_data3={
	'刑侦': {
		'name': '刑侦',
		'type': 'folder',
		'additionalParameters': {
		'id': '1',
			'children': {
				'痕迹检验': {
					'name': '痕迹检验',
					'type': 'item',
					'additionalParameters': {
					'id': '10'
				}
			},
				'声像技术': {
					'name': '声像技术',
					'type': 'item',
					'additionalParameters': {
					'id': '9',
					'item-selected': true
				}
			}
		}
	}
},
	'交警': {
		'name': '交警',
		'type': 'folder',
			'additionalParameters': {
			'id': '32',
			'children': {
				'交通事故': {
					'name': '交通事故',
						'type': 'item',
						'additionalParameters': {
						'id': '33'
					}
				},
				'交通道理管理': {
						'name': '交通道理管理',
						'type': 'item',
						'additionalParameters': {
						'id': '34'
					}
				}
		}
	}
}
}

var tree_data_4 = {
	'车辆使用': {
		'name': '车辆使用',
		'type': 'folder',
		'additionalParameters': {
			'id': '1',
			'children': {
				'车辆申请': {
					'name': '车辆申请',
					'type': 'item',
					'additionalParameters': {
						'id': '11'
					}
				},
				'部门审批': {
					'name': '部门审批',
					'type': 'item',
					'additionalParameters': {
						'id': '12'
					}
				},
				'车辆调度': {
					'name': '车辆调度',
					'type': 'item',
					'additionalParameters': {
						'id': '13'
					}
				},
				'已审批查询': {
					'name': '已审批查询',
					'type': 'item',
					'additionalParameters': {
						'id': '14'
					}
				},
				'我的申请': {
					'name': '我的申请',
					'type': 'item',
					'additionalParameters': {
						'id': '26'
					}
				},
				'已调度查询': {
					'name': '已调度查询',
					'type': 'item',
					'additionalParameters': {
						'id': '30'
					}
				},

			},

		},

	},
	'车辆信息': {
		'name': '车辆信息',
		'type': 'folder',
		'additionalParameters': {
			'id': '2',
			'children': {
				'车辆登记': {
					'name': '车辆登记',
					'type': 'item',
					'additionalParameters': {
						'id': '15'
					}
				},
				'车辆调动': {
					'name': '车辆调动',
					'type': 'item',
					'additionalParameters': {
						'id': '16'
					}
				},
				'车辆处置': {
					'name': '车辆处置',
					'type': 'item',
					'additionalParameters': {
						'id': '17'
					}
				},

			},

		},

	},
	'车队驾驶员管理': {
		'name': '车队驾驶员管理',
		'type': 'folder',
		'additionalParameters': {
			'id': '3',
			'children': {
				'驾驶员信息': {
					'name': '驾驶员信息',
					'type': 'item',
					'additionalParameters': {
						'id': '19'
					}
				},
				'车队管理': {
					'name': '车队管理',
					'type': 'item',
					'additionalParameters': {
						'id': '53'
					}
				},

			},

		},

	},
	'日常管理': {
		'name': '日常管理',
		'type': 'folder',
		'additionalParameters': {
			'id': '4',
			'children': {
				'加油记录': {
					'name': '加油记录',
					'type': 'item',
					'additionalParameters': {
						'id': '40'
					}
				},
				'维修记录': {
					'name': '维修记录',
					'type': 'item',
					'additionalParameters': {
						'id': '41'
					}
				},
				'规费管理': {
					'name': '规费管理',
					'type': 'item',
					'additionalParameters': {
						'id': '42'
					}
				},
				'年检记录': {
					'name': '年检记录',
					'type': 'item',
					'additionalParameters': {
						'id': '43'
					}
				},
				'保险记录': {
					'name': '保险记录',
					'type': 'item',
					'additionalParameters': {
						'id': '44'
					}
				},
				'违章记录': {
					'name': '违章记录',
					'type': 'item',
					'additionalParameters': {
						'id': '45'
					}
				},
				'事故记录': {
					'name': '事故记录',
					'type': 'item',
					'additionalParameters': {
						'id': '46'
					}
				},

			},

		},

	},
	'监控预警': {
		'name': '监控预警',
		'type': 'folder',
		'additionalParameters': {
			'id': '6',
			'children': {
				'时间预警': {
					'name': '时间预警',
					'type': 'item',
					'additionalParameters': {
						'id': '48'
					}
				},
				'借调预警': {
					'name': '借调预警',
					'type': 'item',
					'additionalParameters': {
						'id': '49'
					}
				},
				'保险提示': {
					'name': '保险提示',
					'type': 'item',
					'additionalParameters': {
						'id': '50'
					}
				},
				'年审提示': {
					'name': '年审提示',
					'type': 'item',
					'additionalParameters': {
						'id': '51'
					}
				},

			},

		},

	},
	'用户评价': {
		'name': '用户评价',
		'type': 'folder',
		'additionalParameters': {
			'id': '7',
			'children': {
				'评价状况查看': {
					'name': '评价状况查看',
					'type': 'item',
					'additionalParameters': {
						'id': '28'
					}
				},
				'用户评价': {
					'name': '用户评价',
					'type': 'item',
					'additionalParameters': {
						'id': '29'
					}
				},

			},

		},

	},
	'系统设置': {
		'name': '系统设置',
		'type': 'folder',
		'additionalParameters': {
			'id': '10',
			'children': {
				'角色管理': {
					'name': '角色管理',
					'type': 'item',
					'additionalParameters': {
						'id': '21'
					}
				},
				'用户管理': {
					'name': '用户管理',
					'type': 'item',
					'additionalParameters': {
						'id': '22'
					}
				},
				'菜单管理': {
					'name': '菜单管理',
					'type': 'item',
					'additionalParameters': {
						'id': '23'
					}
				},
				'字典管理': {
					'name': '字典管理',
					'type': 'item',
					'additionalParameters': {
						'id': '24'
					}
				},
				'权限管理': {
					'name': '权限管理',
					'type': 'item',
					'additionalParameters': {
						'id': '25'
					}
				},
				'系统配置': {
					'name': '系统配置',
					'type': 'item',
					'additionalParameters': {
						'id': '52'
					}
				},

			},

		},

	},

}

var tree_data = {
	'for-sale' : {name: 'For Sale', type: 'folder',additionalParameters:{children:{
		'appliances' : {name: 'Appliances', type: 'item'},
		'arts-crafts' : {name: 'Arts & Crafts', type: 'item'},
		'clothing' : {name: 'Clothing', type: 'item'},
		'computers' : {name: 'Computers', type: 'item'},
		'jewelry' : {name: 'Jewelry', type: 'item'},
		'office-business' : {name: 'Office & Business', type: 'item'},
		'sports-fitness' : {name: 'Sports & Fitness', type: 'item'}
	}}}	,
	'vehicles' : {name: 'Vehicles', type: 'folder'}	,
	'rentals' : {name: 'Rentals', type: 'folder'}	,
	'real-estate' : {name: 'Real Estate', type: 'folder'}	,
	'pets' : {name: 'Pets', type: 'folder'}	,
	'tickets' : {name: 'Tickets', type: 'item'}	,
	'services' : {name: 'Services', type: 'item'}	,
	'personals' : {name: 'Personals', type: 'item'}
}
//tree_data['for-sale']['additionalParameters'] = {
//	'children' : {
//		'appliances' : {name: 'Appliances', type: 'item'},
//		'arts-crafts' : {name: 'Arts & Crafts', type: 'item'},
//		'clothing' : {name: 'Clothing', type: 'item'},
//		'computers' : {name: 'Computers', type: 'item'},
//		'jewelry' : {name: 'Jewelry', type: 'item'},
//		'office-business' : {name: 'Office & Business', type: 'item'},
//		'sports-fitness' : {name: 'Sports & Fitness', type: 'item'}
//	}
//}
tree_data['vehicles']['additionalParameters'] = {
	'children' : {
		'cars' : {name: 'Cars', type: 'folder'},
		'motorcycles' : {name: 'Motorcycles', type: 'item'},
		'boats' : {name: 'Boats', type: 'item'}
	}
}
tree_data['vehicles']['additionalParameters']['children']['cars']['additionalParameters'] = {
	'children' : {
		'classics' : {name: 'Classics', type: 'item'},
		'convertibles' : {name: 'Convertibles', type: 'item'},
		'coupes' : {name: 'Coupes', type: 'item'},
		'hatchbacks' : {name: 'Hatchbacks', type: 'item'},
		'hybrids' : {name: 'Hybrids', type: 'item'},
		'suvs' : {name: 'SUVs', type: 'item'},
		'sedans' : {name: 'Sedans', type: 'item'},
		'trucks' : {name: 'Trucks', type: 'item'}
	}
}

tree_data['rentals']['additionalParameters'] = {
	'children' : {
		'apartments-rentals' : {name: 'Apartments', type: 'item'},
		'office-space-rentals' : {name: 'Office Space', type: 'item'},
		'vacation-rentals' : {name: 'Vacation Rentals', type: 'item'}
	}
}
tree_data['real-estate']['additionalParameters'] = {
	'children' : {
		'apartments' : {name: 'Apartments', type: 'item'},
		'villas' : {name: 'Villas', type: 'item'},
		'plots' : {name: 'Plots', type: 'item'}
	}
}
tree_data['pets']['additionalParameters'] = {
	'children' : {
		'cats' : {name: 'Cats', type: 'item'},
		'dogs' : {name: 'Dogs', type: 'item'},
		'horses' : {name: 'Horses', type: 'item'},
		'reptiles' : {name: 'Reptiles', type: 'item'}
	}
}

var treeDataSource = new DataSourceTree({data: tree_data});
var treeDataSource3 = new DataSourceTree({data: tree_data3});
var treeDataSource4 = new DataSourceTree({data: tree_data_4});

function initTreeData(callback){
	var params = {};
	//var treeData;
	params.roleCode = $("#role").val();
	ajaxFunction("/" + PROJECT_NAME + "/menu/tree.do", params, function(data) {
			if (data!=null) {
				//treeData= data;
				callback(data);
			}else{
				alert("初始化失败");
			}
		}, false

	);
	//return treeData;
//    }
}















var tree_data_2 = {
	'pictures'  : {name: 'Pictures',      type: 'folder',    'icon-class':'red'     }	,
	'music'     : {name: 'Music',         type: 'folder',    'icon-class':'orange'  }	,
	'video'     : {name: 'Video',         type: 'folder',    'icon-class':'blue'    }	,
	'documents' : {name: 'Documents',     type: 'folder',    'icon-class':'green'   }	,
	'backup'    : {name: 'Backup',        type: 'folder'}	,
	'readme'    : {name: '<i class="icon-file-text grey"></i> ReadMe.txt', type: 'item'},
	'manual'    : {name: '<i class="icon-book blue"></i> Manual.html', type: 'item'}
}
tree_data_2['music']['additionalParameters'] = {
	'children' : [
		{name: '<i class="icon-file-text   brown"></i> song1.ogg', type: 'item'},
		{name: '<i class="icon-file-text   brown"></i> song2.ogg', type: 'item'},
		{name: '<i class="icon-file-text   brown"></i> song3.ogg', type: 'item'},
		{name: '<i class="icon-file-text   brown"></i> song4.ogg', type: 'item'},
		{name: '<i class="icon-file-text   brown"></i> song5.ogg', type: 'item'}
	]
}
tree_data_2['video']['additionalParameters'] = {
	'children' : [
		{name: '<i class="icon-file-text  brown"></i> movie1.avi', type: 'item'},
		{name: '<i class="icon-file-text  brown"></i> movie2.avi', type: 'item'},
		{name: '<i class="icon-file-text  brown"></i> movie3.avi', type: 'item'},
		{name: '<i class="icon-file-text  brown"></i> movie4.avi', type: 'item'},
		{name: '<i class="icon-file-text  brown"></i> movie5.avi', type: 'item'}
	]
}
tree_data_2['pictures']['additionalParameters'] = {
	'children' : {
		'wallpapers' : {name: 'Wallpapers', type: 'folder', 'icon-class':'pink'},
		'camera' : {name: 'Camera', type: 'folder', 'icon-class':'pink'}
	}
}
tree_data_2['pictures']['additionalParameters']['children']['wallpapers']['additionalParameters'] = {
	'children' : [
		{name: '<i class="icon-file-text brown"></i> wallpaper1.jpg', type: 'item'},
		{name: '<i class="icon-file-text brown"></i> wallpaper2.jpg', type: 'item'},
		{name: '<i class="icon-file-text brown"></i> wallpaper3.jpg', type: 'item'},
		{name: '<i class="icon-file-text brown"></i> wallpaper4.jpg', type: 'item'}
	]
}
tree_data_2['pictures']['additionalParameters']['children']['camera']['additionalParameters'] = {
	'children' : [
		{name: '<i class="icon-file-text brown"></i> photo1.jpg', type: 'item'},
		{name: '<i class="icon-file-text brown"></i> photo2.jpg', type: 'item'},
		{name: '<i class="icon-file-text brown"></i> photo3.jpg', type: 'item'},
		{name: '<i class="icon-file-text brown"></i> photo4.jpg', type: 'item'},
		{name: '<i class="icon-file-text brown"></i> photo5.jpg', type: 'item'},
		{name: '<i class="icon-file-text brown"></i> photo6.jpg', type: 'item'}
	]
}


tree_data_2['documents']['additionalParameters'] = {
	'children' : [
		{name: '<i class="icon-file-text brown"></i> document1.pdf', type: 'item'},
		{name: '<i class="icon-file-text brown"></i> document2.doc', type: 'item'},
		{name: '<i class="icon-file-text brown"></i> document3.doc', type: 'item'},
		{name: '<i class="icon-file-text brown"></i> document4.pdf', type: 'item'},
		{name: '<i class="icon-file-text brown"></i> document5.doc', type: 'item'}
	]
}

tree_data_2['backup']['additionalParameters'] = {
	'children' : [
		{name: '<i class="icon-file-text brown"></i> backup1.zip', type: 'item'},
		{name: '<i class="icon-file-text brown"></i> backup2.zip', type: 'item'},
		{name: '<i class="icon-file-text brown"></i> backup3.zip', type: 'item'},
		{name: '<i class="icon-file-text brown"></i> backup4.zip', type: 'item'}
	]
}
var treeDataSource2 = new DataSourceTree({data: tree_data_2});
