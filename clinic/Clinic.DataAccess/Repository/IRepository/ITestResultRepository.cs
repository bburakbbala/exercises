using Clinic.Models;

namespace Clinic.DataAccess.Repository.IRepository
{
    public interface ITestResultRepository : IRepositoryAsync<TestResult>
    {
        void Update(TestResult testResult);
    }
}