(ns meetup)

(defn start-of-year [year]
  (mod (+ 1  (* 5 (mod (dec year) 4)) (* 4 (mod (dec year) 100)) (* 6 (mod (dec year) 400)) ) 7)
)

(def day_index {:sunday 0 :monday 1 :tuesday 2 :wednesday 3 :thursday 4 :friday 5 :saturday 6})

(defn leap-year? [year]
  (and (= 0 (mod year 4)) (or (not= 0 (mod year 100)) (= 0 (mod year 400))))
)

(defn month-durations [year]
  [31 (if (leap-year? year) 29 28) 31 30 31 30 31 31 30 31 30 31]
)

(defn start-of-month [year month]
  (mod (+ (start-of-year year) (apply + (take (dec month) (month-durations year)))) 7)
)

(defn get-last-such-day-before [limit]
  (fn [day] (apply max (map #(mod (+ day %) limit) (filter #(> limit %) [0 7 14 21 28]))))
)

(defn select [description month year]
  (cond
    (= description :first) identity
    (= description :second) #(+ % 7)
    (= description :third) #(+ % 14)
    (= description :fourth) #(+ % 21)
    (= description :teenth) (get-last-such-day-before 20)
    (= description :last) (get-last-such-day-before (inc ((month-durations year) (dec month))))
  )
)

(defn meetup [month year day description]
  (let [start (start-of-month year month) day_number (day_index day)
        first_such_day (inc (mod (- day_number start) 7))]
    [year month ((select description month year) first_such_day)]
  )
)
