var date = new Date()

function toStringByFormatting(source) {
    const year = source.getFullYear();
    const month = leftPad(source.getMonth() + 1);
    const day = leftPad(source.getDate());

    var str = "" + year + month + day;
    return str;
}

function leftPad(value) {
    if (value >= 10) {
        return value;
    }

    return "" + "0" + value;
}

function getPrevMeal() {
    date.setDate(date.getDate() - 1)
    getMeal(toStringByFormatting(date))
}

function getNextMeal() {
    date.setDate(date.getDate() + 1)
    getMeal(toStringByFormatting(date))
}

function getCurrentMeal() {
    getMeal(toStringByFormatting(date))
}

function getMeal(searchDate) {
    $.ajax({
        url : "http://localhost:8080/api/school/cafeteria?MLSV_FROM_YMD=" + searchDate + "&MLSV_TO_YMD=" + searchDate,
        method: "GET",
        dataType: "json"
    }).done(function(mealDto) {
        setHttpMeal(mealDto.mealInfo[0])
    }).fail(function() {
        $(".meal-list").empty();
        $(".meal-date").text(toStringByFormatting(date));
        $(".meal-list").append("<h5>오늘은 식사가 없어요</h5>")
    })
}

function setHttpMeal(meal) {
    $(".meal-list").empty()
    meal.dish.forEach(function(dish) {
        $(".meal-list").append("<h5>" + dish + "</h5>");
        console.log(dish);
    })

    $(".meal-date").text(meal.year + "" + leftPad(meal.month) + "" + leftPad(meal.day));
}

getCurrentMeal()