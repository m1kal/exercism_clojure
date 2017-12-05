(ns clock)

(defn clock [hours minutes]
  [(mod (+ hours (int (Math/floor (/ minutes 60)))) 24)  (mod minutes 60)]
)

(defn clock->string [time]
  (format "%02d:%02d" (first time) (last time))
)

(defn add-time [time minutes]
  (clock (first time) (+ (last time) minutes))
)
