(ns bob)

(defn response-for [string] ;; <- arglist goes here
  (cond
    (and
      (not= string (clojure.string/lower-case string))
      (= string (clojure.string/upper-case string))
    ) "Whoa, chill out!"
    (re-matches #".*\?$" string) "Sure."
    (re-matches #"\s*" string) "Fine. Be that way!"
    :else "Whatever.")
)
