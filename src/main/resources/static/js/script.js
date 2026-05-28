
let currTheme = getTheme();

document.addEventListener('DOMContentLoaded', () => {
    changeTheme();
})


function changeTheme() {
    document.querySelector('html').classList.add(currTheme);

    const btn = document.querySelector('#theme_change_btn');
    console.log("Theme is changing.");
    let oldTheme = currTheme;

    btn.addEventListener("click", (e) => {
        if(currTheme == "light") {
            currTheme = "dark";
        } else {
            currTheme = "light";
        }

        setTheme(currTheme);
        document.querySelector('html').classList.remove(oldTheme);
        document.querySelector('html').classList.add(currTheme);

        btn.querySelector('span').textContent = currTheme == 'light' ? 'Dark': 'Light';
    })

}

function setTheme(theme) {
    localStorage.setItem("theme",theme);
}

function getTheme() {
    let theme = localStorage.getItem("theme");
    return theme ? theme : "light";
}