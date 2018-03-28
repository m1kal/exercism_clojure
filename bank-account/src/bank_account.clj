(ns bank-account)

(defn open-account []
  (atom {:balance 0})
)

(defn close-account [account]
  (reset! account {})
)

(defn get-balance [account]
  (:balance @account)
)

(defn update-balance [account amount]
  (swap! account assoc :balance (+ (get-balance account) amount))
)
