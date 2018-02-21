(ns dzik.cleverbot
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]
            [ring.util.codec :as codec]))


(def bot
  (client/post "https://cleverbot.io/1.0/create" {:form-params 
                                                  {:user "2oMm1kKFNKLyP5jq" 
                                                   :key "1afstTSoXjRqkMPxrAuIHtj5FgnmBqx2" 
                                                   :nick "fnord"}
                                                  :content-type :json
                                                  :insecure? true
                                                  :accept :json}))

