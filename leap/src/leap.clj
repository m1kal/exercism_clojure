(ns leap)

(defn leap-year? [year]
  (and (= 0 (mod year 4)) (or (not= 0 (mod year 100)) (= 0 (mod year 400))))
)
