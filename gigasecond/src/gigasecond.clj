(ns gigasecond)

(def days_in_gigasecond (Math/floor (/ 1000000000 60 60 24)))

(def short_months [4 6 9 11])

(defn leap [year]
  (and (zero? (mod year 4)) ( or (not (zero? (mod year 100))) (zero? (mod year 400))))
  )

(defn last-day-of-month [date]
  (or
    (and (= (date :month) 2)
      (if (leap (date :year))
        (= (date :day) 29)
        (= (date :day) 28)))
    (and (some #{(date :month)} short_months) (= (date :day) 30))
    (= (date :day) 31)
  ))

(defn add-day [date]
  (if (last-day-of-month date)
    (if (= (date :month) 12)
      {:year (inc (date :year)) :month 1 :day 1}
      {:year (date :year) :month (inc (date :month)) :day 1})
    {:year (date :year) :month (date :month) :day (inc (date :day))})
)

(defn from [year month day]
  (vals (reduce (fn [current, _] (add-day current)) {:year year :month month :day day} (range days_in_gigasecond)))
)
