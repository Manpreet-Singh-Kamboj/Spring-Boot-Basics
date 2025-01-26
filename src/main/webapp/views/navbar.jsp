<nav class="flex items-center justify-between bg-blue-500 p-3 text-white" aria-label="Main navigation">
  <div class="flex flex-row items-center gap-6">
    <div class="text-xl font-bold">NoteTakingApp</div>
    <div class="hidden items-center justify-center gap-5 md:flex">
      <a href="index.jsp" class="text-gray-200 transition-all duration-300 hover:text-white">Home</a>
      <a href="add_note.jsp" class="text-gray-200 transition-all duration-300 hover:text-white">Add Note</a>
      <a href="all_notes.jsp" class="text-gray-200 transition-all duration-300 hover:text-white">Show All Notes</a>
    </div>
  </div>
  <button id="menu-btn" class="block text-white md:hidden" aria-expanded="false" aria-controls="mobile-menu" onClick="showMobileMenu()">
    <svg fill="white" height="24" viewBox="0 0 24 24" width="24" xmlns="http://www.w3.org/2000/svg"><path d="M4 6H20M4 12H20M4 18H20" stroke="white" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" /></svg>
  </button>
</nav>
<div id="mobile-menu" class="flex flex-col gap-3 bg-blue-600 p-3 md:hidden transition-all duration-200 ease-in opacity-0 max-h-0 w-[calc(100%-60%)] z-50 absolute right-0">
    <a href="index.jsp" class="text-gray-200 transition-all duration-300 hover:text-white">Home</a>
    <a href="add_note.jsp" class="text-gray-200 transition-all duration-300 hover:text-white">Add Note</a>
    <a href="all_notes.jsp" class="text-gray-200 transition-all duration-300 hover:text-white">Show All Notes</a>
</div>