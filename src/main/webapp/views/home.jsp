<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Note Taker</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="https://unpkg.com/@tailwindcss/browser@4"></script>
    <script src="javascript/script.js"></script>
  </head>
  <body class="p-0 m-0">
    <div>
    <%@include file="navbar.jsp" %>
    </div>
    <div>
    <section class="flex justify-center items-center mx-10 my-10 px-10 flex-col border rounded-xl">
      <img src="https://cdn3.vectorstock.com/i/1000x1000/42/82/note-taking-app-icon-mobile-application-vector-35444282.jpg" class="max-w-[calc(100%-70%)] max-h-[calc(100%-70%)]"/>
      <div class="py-10 flex flex-col justify-center items-center gap-4">
        <h2 class="font-bold text-3xl">
          Start taking your notes today
        </h2>
        <a href="add_note.jsp" class="bg-blue-500 hover:bg-blue-600 transition-all duration-300 ease-in text-white font-semibold px-10 py-2 rounded-lg">
          Start
        </a>
      </div>
    </section>
  </body>
</html>