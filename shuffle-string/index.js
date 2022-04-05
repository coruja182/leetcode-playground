const MAX_CHARS = 100
const CHARS_PATTERN = /^[a-z]+$/

/**
 * Organized the string using the indices array in ascending order.
 * 
 * Input: s = "codeleet", indices = [4,5,6,7,0,2,1,3]
 * Output: "leetcode"
 * Explanation: As shown, "codeleet" becomes "leetcode" after shuffling.
 * @param {string} text
 * @param {number[]} indices
 * @returns {string} the restored string
 */
const restoreString = (text, indices) => {
  // validations
  if (!text || !indices || indices.length > 100) {
    throw new Error('text and indices length must be between 1 and 100')
  }

  if (text.length !== indices.length) {
    throw new Error('text and indices should have same length')
  }

  if (!CHARS_PATTERN.test(text)) {
    throw new Error('only lowercase English letters are allowed')
  }

  const resultCharArray = []
  indices.forEach((value, index, values) => {
    if (resultCharArray[value]) {
      throw new Error(`duplicated index '${value}' is not allowed`)
    }

    if (value >= MAX_CHARS || value < 0 || value >= values.length) {
      throw new Error(`invalid index '${value}'`)
    }

    resultCharArray[value] = text.charAt(index)
  })
  return resultCharArray.join('')
}

module.exports = {
  MAX_CHARS,
  restoreString
}
