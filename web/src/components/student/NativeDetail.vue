<template>
  <div>
    <div>
        <h1>{{ tableData.title }}</h1>
    </div>
    <p>简介</p>
    <div>
        <h3>
            {{ tableData.intro }}
        </h3>
    </div>
    <p>内容</p>
    <div>{{ tableData.content }}</div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useRoute } from "vue-router";

let route = useRoute()
let {id} = route.query
let tableData = ref({})
let getData = async ()=>{
    let res = await $axios.get('/native/get/'+id)
    tableData.value = res.data.data
}
getData()
</script>

<style scoped>
div{
    margin-bottom: 40px;
}
</style>