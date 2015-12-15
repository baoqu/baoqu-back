(ns baoqu.app
  (:require [catacumba.core :as cat]
            [catacumba.handlers.misc :as misc]
            [catacumba.handlers.parse :as parse]
            [baoqu.routes.home :as home]
            [baoqu.routes.users :as users]
            [baoqu.routes.events :as events]
            [baoqu.init.fixtures :as fix])
  (:gen-class))

(def app
  (cat/routes [
               [:prefix "api"
                [:any (misc/autoreloader)]
                [:any (parse/body-params)]
                [:get "meta" #'home/hello-baoqu]
                [:prefix "users"
                 [:post "create" #'users/create]]
                [:prefix "events"
                 [:post "create" #'events/create]]]]))

(defn -main
  [& args]
  (cat/run-server app)
  (fix/load-all))
