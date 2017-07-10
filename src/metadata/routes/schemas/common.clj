(ns metadata.routes.schemas.common
  (:use [common-swagger-api.schema :only [describe StandardUserQueryParams]])
  (:require [schema.core :as s])
  (:import [java.util UUID]))

(def DataTypes ["file" "folder"])
(def TargetTypes (concat DataTypes ["analysis" "app" "user"]))

(def TargetIdPathParam (describe UUID "The target item's UUID"))
(def TargetIdParam TargetIdPathParam)
(def TargetTypeEnum (apply s/enum TargetTypes))

(def DataTypeEnum (apply s/enum DataTypes))
(def DataTypeParam (describe DataTypeEnum "The type of the requested data item."))

(s/defschema StandardDataItemQueryParams
  (assoc StandardUserQueryParams
    :data-type DataTypeParam))

(s/defschema AvuSearchQueryParams
  (assoc StandardUserQueryParams
    (s/optional-key :attribute)   (describe [String] "Attribute names to search for.")
    (s/optional-key :target-type) (describe [TargetTypeEnum] "Target types to search for.")
    (s/optional-key :value)       (describe [String] "Values to search for.")
    (s/optional-key :unit)        (describe [String] "Units to search for.")))

(s/defschema TargetIDList
  {:target-ids (describe [UUID] "A list of target IDs")})

(s/defschema DataIdList
  {:filesystem (describe [UUID] "A list of UUIDs, each for a file or folder")})

(s/defschema TargetFilterRequest
  {:target-ids
   (describe [UUID] "List of IDs to filter")

   :target-types
   (describe [TargetTypeEnum] "The types of the given IDs")})

(s/defschema TargetItem
  {:id   TargetIdParam
   :type (describe TargetTypeEnum "The type of this data item")})

(s/defschema TargetItemList
  {:targets (describe [TargetItem] "A list of target items")})
