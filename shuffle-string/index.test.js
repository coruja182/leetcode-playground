const { restoreString, MAX_CHARS } = require('./index')

describe('The restoreString function', () => {
  it('SHOULD return an Error WHEN called with undefined string and undefined indexes', () => {
    const result = () => restoreString()
    expect(result).toThrowError('text and indices length must be between 1 and 100')
  })

  it('SHOULD return an Error WHEN called with undefined string and defined indexes', () => {
    const result = () => restoreString(undefined, [])
    expect(result).toThrowError('text and indices length must be between 1 and 100')
  })

  it('SHOULD return an Error WHEN called with a defined string and undefined indexes', () => {
    const result = () => restoreString('string')
    expect(result).toThrowError('text and indices length must be between 1 and 100')
  })

  it('SHOULD return an Error WHEN called with an empty string an empty array of indexes', () => {
    const result = () => restoreString('', [])
    expect(result).toThrowError('text and indices length must be between 1 and 100')
  })

  it('SHOULD return an Error WHEN the input string and indices does not have same length', () => {
    const result = () => restoreString('aa', [1])
    expect(result).toThrowError('text and indices should have same length')
  })

  it('SHOULD return an Error WHEN non english letters are used as input', () => {
    const result = () => restoreString('@', [0])
    expect(result).toThrowError('only lowercase English letters are allowed')
  })

  it('SHOULD return an Error WHEN a duplicated index is provided', () => {
    const result = () => restoreString('cba', [2, 0, 2])
    expect(result).toThrowError('duplicated index \'2\' is not allowed')
  })

  it('SHOULD return an Error WHEN uppercase letters are provided', () => {
    const result = () => restoreString('A', [0])
    expect(result).toThrowError('only lowercase English letters are allowed')
  })

  it('SHOULD return en Error WHEN an index is not within valid range 0 <= indices[i] < n', () => {
    const result = () => restoreString('cba', [2,3,0])
    expect(result).toThrowError('invalid index \'3\'')
  })

  it('SHOULD return en Error WHEN text length is greater than max length (100)', () => {
    const result = () => restoreString('a'.repeat(MAX_CHARS), Array.from(Array(MAX_CHARS + 1).keys()))
    expect(result).toThrowError('text and indices length must be between 1 and 100')
  })

  it.each([
    ['a', 'a', [0]],
    ['ab', 'ba', [1, 0]],
    ['abc', 'bac', [1, 0, 2]],
    ['aaaab', 'aabaa', [0, 1, 4, 3, 2]],
    ['leetcode', 'codeleet', [4, 5, 6, 7, 0, 2, 1, 3]],
    ['a'.repeat(MAX_CHARS), 'a'.repeat(MAX_CHARS), Array.from(Array(MAX_CHARS).keys())], // testing the limits
  ])('SHOULD return \'%s\' WHEN called with [%s, %p]', (expectedResult, text, indices) => {
    const result = restoreString(text, indices)
    expect(result).toEqual(expectedResult)
  })
})
