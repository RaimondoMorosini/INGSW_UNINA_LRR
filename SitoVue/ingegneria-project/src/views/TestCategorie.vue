<template>
  <div class="container">
    <div>
      <label>File
        <input type="file" ref="fileInput" accept="image/*,.pdf" @change="handleFileUpload($event)"/>
      </label>
      <br/>
      <br/>
      <button v-on:click="submitFile()">Submit</button>
    </div>
  </div>
</template>

