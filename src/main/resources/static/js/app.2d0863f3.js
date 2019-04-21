(function(t){function e(e){for(var o,i,l=e[0],u=e[1],s=e[2],d=0,p=[];d<l.length;d++)i=l[d],r[i]&&p.push(r[i][0]),r[i]=0;for(o in u)Object.prototype.hasOwnProperty.call(u,o)&&(t[o]=u[o]);c&&c(e);while(p.length)p.shift()();return n.push.apply(n,s||[]),a()}function a(){for(var t,e=0;e<n.length;e++){for(var a=n[e],o=!0,l=1;l<a.length;l++){var u=a[l];0!==r[u]&&(o=!1)}o&&(n.splice(e--,1),t=i(i.s=a[0]))}return t}var o={},r={app:0},n=[];function i(e){if(o[e])return o[e].exports;var a=o[e]={i:e,l:!1,exports:{}};return t[e].call(a.exports,a,a.exports,i),a.l=!0,a.exports}i.m=t,i.c=o,i.d=function(t,e,a){i.o(t,e)||Object.defineProperty(t,e,{enumerable:!0,get:a})},i.r=function(t){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(t,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(t,"__esModule",{value:!0})},i.t=function(t,e){if(1&e&&(t=i(t)),8&e)return t;if(4&e&&"object"===typeof t&&t&&t.__esModule)return t;var a=Object.create(null);if(i.r(a),Object.defineProperty(a,"default",{enumerable:!0,value:t}),2&e&&"string"!=typeof t)for(var o in t)i.d(a,o,function(e){return t[e]}.bind(null,o));return a},i.n=function(t){var e=t&&t.__esModule?function(){return t["default"]}:function(){return t};return i.d(e,"a",e),e},i.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)},i.p="/";var l=window["webpackJsonp"]=window["webpackJsonp"]||[],u=l.push.bind(l);l.push=e,l=l.slice();for(var s=0;s<l.length;s++)e(l[s]);var c=u;n.push([0,"chunk-vendors"]),a()})({0:function(t,e,a){t.exports=a("cd49")},"5c0b":function(t,e,a){"use strict";var o=a("6879"),r=a.n(o);r.a},6879:function(t,e,a){},cd49:function(t,e,a){"use strict";a.r(e);a("cadf"),a("551c"),a("f751"),a("097d");var o=a("2b0e"),r=a("bb71");a("da64"),a("d1e7");o["default"].use(r["a"],{iconfont:"md"});var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("v-app",[a("v-toolbar",{attrs:{app:""}},[a("v-toolbar-title",[t._v("BOOK MANAGER")])],1),a("v-content",[a("v-container",{staticClass:"container",attrs:{fluid:""}},[a("v-layout",{attrs:{row:"",wrap:""}},[t._l(t.books,function(e){return[a("v-flex",{attrs:{"pt-3":"",xs12:""}},[a("v-card",{staticClass:"card",on:{click:function(a){return a.stopPropagation(),t.showUpdateDialog(e.id)}}},[a("v-card-title",[a("h1",[t._v(t._s(e.title))])]),a("v-card-text",[a("div",[t._v(t._s(e.author.name))]),a("div",[t._v(t._s(e.publisher.name))])])],1)],1)]})],2)],1),a("v-btn",{attrs:{color:"pink",dark:"",fixed:"",bottom:"",right:"",fab:""},on:{click:function(e){e.stopPropagation(),t.createDialog=!0}}},[t._v("+")]),a("v-dialog",{staticClass:"create-dialog",attrs:{"max-width":"500"},model:{value:t.createDialog,callback:function(e){t.createDialog=e},expression:"createDialog"}},[a("v-card",[a("v-card-title",{staticClass:"headline grey lighten-2"},[t._v("新規登録")]),a("v-card-text",[a("v-form",{ref:"createForm",attrs:{"lazy-validation":""}},[a("v-text-field",{attrs:{label:"タイトル",required:"",counter:100,value:t.newBook.title}}),a("v-autocomplete",{attrs:{items:t.authorItems,label:"著者"},model:{value:t.newBook.author.id,callback:function(e){t.$set(t.newBook.author,"id",e)},expression:"newBook.author.id"}}),a("v-autocomplete",{attrs:{items:t.publisherItems,label:"出版社"},model:{value:t.newBook.publisher.id,callback:function(e){t.$set(t.newBook.publisher,"id",e)},expression:"newBook.publisher.id"}}),a("v-btn",{attrs:{color:"info"},on:{click:t.createBook}},[t._v("登録")])],1)],1)],1)],1),a("v-dialog",{staticClass:"update-dialog",attrs:{"max-width":"500"},model:{value:t.updateDialog,callback:function(e){t.updateDialog=e},expression:"updateDialog"}},[a("v-card",[a("v-card-title",{staticClass:"headline grey lighten-2"},[t._v("更新")]),a("v-card-text",[a("v-form",[a("v-text-field",{attrs:{label:"タイトル",required:"",counter:100,value:t.selectedBook.title}}),a("v-autocomplete",{attrs:{items:t.authorItems,label:"著者"},model:{value:t.selectedBook.author.id,callback:function(e){t.$set(t.selectedBook.author,"id",e)},expression:"selectedBook.author.id"}}),a("v-autocomplete",{attrs:{items:t.publisherItems,label:"出版社"},model:{value:t.selectedBook.publisher.id,callback:function(e){t.$set(t.selectedBook.publisher,"id",e)},expression:"selectedBook.publisher.id"}}),a("v-btn",{attrs:{color:"info"}},[t._v("更新")]),a("v-btn",{attrs:{color:"error"}},[t._v("削除")])],1)],1)],1)],1)],1)],1)},i=[],l=(a("7f7f"),a("7514"),a("d225")),u=a("b0b4"),s=a("308d"),c=a("6bb5"),d=a("4e2b"),p=a("9ab4"),f=a("60a3"),b=a("bc3a"),v=a.n(b),h=function(t){function e(){var t;return Object(l["a"])(this,e),t=Object(s["a"])(this,Object(c["a"])(e).apply(this,arguments)),t.books=[],t.selectedBook={id:0,title:"",author:{id:0,name:""},publisher:{id:0,name:""}},t.newBook={id:0,title:"",author:{id:0,name:""},publisher:{id:0,name:""}},t.createDialog=!1,t.updateDialog=!1,t.authors=[],t.publishers=[],t}return Object(d["a"])(e,t),Object(u["a"])(e,[{key:"mounted",value:function(){var t=this;v.a.get("/api/book/get").then(function(e){t.books=e.data}),v.a.get("/api/author/get").then(function(e){t.authors=e.data}),v.a.get("/api/publisher/get").then(function(e){t.publishers=e.data})}},{key:"showUpdateDialog",value:function(t){this.updateDialog=!0,this.selectedBook=this.books.find(function(e){return e.id===t})}},{key:"createBook",value:function(){this.$refs.createForm.validate()?console.log(this.newBook):console.log("validation failed")}},{key:"authorItems",get:function(){return this.authors.map(function(t){return{value:t.id,text:t.name}})}},{key:"publisherItems",get:function(){return this.publishers.map(function(t){return{value:t.id,text:t.name}})}}]),e}(f["b"]);h=p["a"]([f["a"]],h);var m=h,g=m,k=(a("5c0b"),a("2877")),x=a("6544"),y=a.n(x),w=a("7496"),B=a("c6a6"),_=a("8336"),O=a("b0af"),V=a("99d9"),j=a("12b2"),D=a("a523"),C=a("549c"),T=a("169a"),P=a("0e8f"),$=a("4bd4"),I=a("a722"),F=a("2677"),M=a("71d9"),S=a("2a7f"),A=Object(k["a"])(g,n,i,!1,null,null,null),q=A.exports;y()(A,{VApp:w["a"],VAutocomplete:B["a"],VBtn:_["a"],VCard:O["a"],VCardText:V["a"],VCardTitle:j["a"],VContainer:D["a"],VContent:C["a"],VDialog:T["a"],VFlex:P["a"],VForm:$["a"],VLayout:I["a"],VTextField:F["a"],VToolbar:M["a"],VToolbarTitle:S["a"]}),o["default"].config.productionTip=!1,new o["default"]({render:function(t){return t(q)}}).$mount("#app")}});
//# sourceMappingURL=app.2d0863f3.js.map